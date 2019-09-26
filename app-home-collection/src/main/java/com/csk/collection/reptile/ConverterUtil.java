package com.csk.collection.reptile;

import java.io.File; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
 
import com.csk.collection.reptile.cache.IReptileCache;
import com.csk.collection.reptile.cache.impl.FileReotileCacheClient;
import com.csk.collection.reptile.converter.IConverterObject;
import com.csk.collection.reptile.converter.impl.ConverterListString;
import com.csk.collection.reptile.converter.impl.ConverterString; 
import com.csk.collection.reptile.converter.model.ConverterModel;
import com.csk.collection.reptile.exception.ReptileBusinessException;
import com.csk.collection.reptile.handler.IHandler;
import com.csk.collection.reptile.handlerevent.IHandlerEvent;
import com.csk.collection.reptile.handlerevent.enums.HandlerEventEnum;
import com.csk.tool.log.Log;
import com.csk.tool.log.dao.impl.ConsoleLog;
import com.csk.tool.single.SingleUtil;
import com.csk.tool.string.StringTemplateUtils;
import com.csk.tool.xml.Node;

public class ConverterUtil {
	
	private static Map<String,IConverterObject>  converterModelMap;
	static Log log=SingleUtil.get(Log.class);
	static{
		log.add(new ConsoleLog());
		converterModelMap=new HashMap<String,IConverterObject>();
		converterModelMap.put("ListString", new ConverterListString());
		converterModelMap.put("String",  new ConverterString()); 
	}
	private static List<ConverterModel> readList(Node node){
		List<ConverterModel> result=new ArrayList<ConverterModel>();
		node.findList("matching").forEach(m->{
			result.add(read(m));
		});
		return result;
	}

	private static List<IHandler> getHandlers(Node node){
		List<IHandler> result=new ArrayList<IHandler>();
		List<Node> nodes= node.findList("handler");
		if(nodes!=null){
			for(Node n:nodes){
				result.add( getHandler(n));
			}
		}
		return result;
	}
	private static IHandler getHandler(Node node){
		String classesString= node.findNodeValue("handler-class");
		if(classesString!=null){
			try {
				Class<?> classes=Class.forName(classesString) ; 
				if(IHandler.class.isAssignableFrom(classes)){
					try {
						IHandler hander=(IHandler) classes.newInstance();
						hander.init(node);
						return hander;
					} catch (Exception e) {
						e.printStackTrace();
						throw new ReptileBusinessException("new tager class "+classesString+"  error "+e.getMessage()); 
					}
				}else{
					throw new ReptileBusinessException("tager class "+classesString+" not extends IHandler ");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new ReptileBusinessException("ClassNotFoundException "+classesString); 
			}
		}
		throw new ReptileBusinessException("not find "+classesString+" class");
	}
	private static  ConverterModel read(Node node){
		ConverterModel model=new ConverterModel();
	 
		model.setName(node.findNodeValue("name") );
		model.setMatchingType( node.findNodeValue("type"));  
		model.setMatchingValue(node.findNodeValue("value")); 
		model.setTarget(node.findNodeValue("target") );
		Node  handlers= node.find("handlers");
		if(handlers!=null){
			model.getHandlers().addAll(getHandlers(handlers));
		}
		if(!converterModelMap.containsKey(node.findNodeValue("matchingType") )){
			throw new ReptileBusinessException("not find "+node.findNodeValue("matchingType")+" IConverterObject ");
		} else{
			model.setConverterObject( converterModelMap.get(node.findNodeValue("matchingType") ));
		}
		return model; 
	} 
	public static ConverterNode toNode(Node m,ApplicationContextConverter app){
		ConverterNode result=new ConverterNode(); 
			String url=m.findNodeValue("url");
			result.setUrl(url);
			Node matching=m.find("matching");
			Node matchingData=m.find("matchingData"); 
			Node handlerEvents=m.find("handler-events");
			Node key=m.find("key");
			if(key!=null){
				result.setKey( readKey(key));  
			}
			if(handlerEvents!=null){
				result.setHandlerEvent(readHandlerEvent(handlerEvents));
			}
			if(matching!=null){
				result.setChildsConverterModel(read(matching));
			}
			if(matchingData!=null){
				result.setChildsConverterModelData(readList(matchingData));
			}
			Node data=m.find("data"); 
			if(data!=null){
				result.setConverterModel(readList(data)); 
			}
			List<Node> childsList=m.findList("childs");
			if(childsList.size()>0){
				for(Node childs:childsList){ 
					Node reptile=childs.find("reptile");
					ConverterNode childsConverterNode= toNode(reptile,app);
					childsConverterNode.setParent(result);
					result.getChilds().add(childsConverterNode);
				} 
			} 
		return result;
	}
	private static ConverterKey readKey(Node key) {
		ConverterKey result=new ConverterKey();
		String time=key.findNodeValue("time");
		result.setKey(key.findNodeValue("key") );
		if(!"".equals(time)){
			result.setTime(Long.parseLong( time));
		}
		return result;
	}

//	private static HandlerEventEnum findHandlerEventEnum(String type){
//		HandlerEventEnum[]values= HandlerEventEnum.values();
//		for(HandlerEventEnum value:values){
//			if(value.getType().equals(type)){
//				return value;
//			}
//		}
//		return null;
//	}
	private static List<IHandlerEvent> readHandlerEvent(Node handlerEvents) {
		List<IHandlerEvent> result=new ArrayList<IHandlerEvent>();
		List<Node> list=handlerEvents.findList("handler-event");
		for(Node m:list){
			String classesString=m.findNodeValue("handler-event-class"); 
			Class<?> classes;
			try {
				classes = Class.forName(classesString);
				if(IHandlerEvent.class.isAssignableFrom(classes)){
					try {
						IHandlerEvent item=(IHandlerEvent)classes.newInstance();
						item.init(m);
						result.add(item);
					} catch (InstantiationException | IllegalAccessException e) {
						throw new ReptileBusinessException(classesString+"  init error "+e.getMessage());  
					} 
				}else{
					throw new ReptileBusinessException(classesString+"not extends class  IHandlerEvent "); 
				}
			} catch (ClassNotFoundException e) {
				throw new ReptileBusinessException("not find class "+classesString); 
			}
			 
		} 
		return result;
	}
	private static IReptileCache getReptileCache(Node node){
		if(node!=null){ 
			String classesString=node.findNodeValue("cache-class");
			if(classesString!=null){
				try {
					Class<?> classes= Class.forName(classesString);
					if(IReptileCache.class.isAssignableFrom(classes)){
						IReptileCache result=(IReptileCache)classes.newInstance();
						result.init(node);
						return result;
					}
				} catch ( Exception e) {
					e.printStackTrace();
				}
			}
		}
		return new FileReotileCacheClient();
	}
	public static ApplicationContextConverter loadApplicationContext(Node node){
		ApplicationContextConverter app=new ApplicationContextConverter();
		Node cacheNode=null;
		if(node!=null){
			 cacheNode=node.find("cache");
		}
		IReptileCache cache= getReptileCache(cacheNode);
		app.setCache(cache);
		return app;
	}
	public static List<ConverterNode> load(File file) throws Exception{
		Node node=Node.readNode(file.getAbsolutePath());
		List<ConverterNode> result=new ArrayList<ConverterNode>();
		Node applicationContext= node.find("applicationContext"); 
		ApplicationContextConverter app=loadApplicationContext(applicationContext);
		node.findList("reptile").forEach(m->{
			ConverterNode con=toNode(m,app);
			con.setApplicationContextConverter(app);
			result.add(con); 
		});
		return result;
	}
 
	public static void main(String ...args) throws Exception{
		List<ConverterNode> node= load(new File("src\\main\\resources\\reptile.xml"));
		ConverterData data=new ConverterData();
		data.setUrl(node.get(0).getUrl());
		data.setConverterNode(node.get(0));
		read(data);
		
	}
	public static Map<String,Object> converterData(ConverterData node,String html,List<ConverterModel> list){
		Map<String,Object> param=new HashMap<String,Object>();
		if(list!=null){ 
			for(ConverterModel model:list){
				if(model.getName()!=null&&model.getName().length()>0){
					Object o=model.getConverterObject().converter(model, node.getConverterNode(), html);
					param.put(model.getName(),hander(model ,o));
				} 
			}
		}
		return param;
	}
	private static Object hander(ConverterModel model,Object o){
		//鏁版嵁澶勭悊鍜岃浆鎹�
		for(IHandler hander: model.getHandlers()){
			o=hander.Handler(o);
		}
		return o;
	}
	public static void analyticalParent(ConverterNode node,ConverterData pdata){ 
		ConverterModel model= node.getChildsConverterModel();
		//瑙ｆ瀽瀛愯妭鐐�
		if(model!=null){
			Object o=model.getConverterObject().converter(model, pdata.getConverterNode(),
					pdata.getHtml()); 
			List data=(List)o; 
			List<ConverterData> content=new ArrayList<ConverterData>(); 
			for(int i=0;i<data.size();i++){
				ConverterData item=new ConverterData();
				item.setConverterNode(node);
				Map<String,Object> param=converterData(item,data.get(i).toString(),node.getChildsConverterModelData());
				item.getData().put("$param", param);
				item.getData().put("$paran", pdata.getData());  
				item.getData().put("$index", i);
				item.getData().put("$size", data.size());
//				System.out.println(JSONObject.toJSON(item.getData()));
				pdata.getChilds().add(item); 
				if(node.getUrl()!=null){
					item.setUrl(StringTemplateUtils.render(node.getUrl() , item.getData()));	 
				} 
				content.add(item);
			}
			for(ConverterData item:content){
				handlerEvent(item,HandlerEventEnum.ANALYSISI_NODE_BEFORE); 
				if(item.getUrl()!=null&&item.getUrl().length()>0){
					try {
						read(item);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				handlerEvent(item,HandlerEventEnum.ANALYSISI_NODE_AFTER); 
			}
		} 
	} 
	public static  void handlerEvent(ConverterData node,HandlerEventEnum enums){
		node.getConverterNode().getHandlerEvent().forEach(m->{
			if(m.getHandlerEventEnum().getType().equals(enums.getType())){
				m.handlerEvent(node);
			}
		});
	}
	public static Response connection(ConverterData node) throws  Exception{
		handlerEvent(node,HandlerEventEnum.SEND_BEFORE); 
		String url=node.getUrl(); 
		Connection conn=Jsoup.connect(url);
		conn.headers(node.getRequestHeader());
		Response res=conn.execute();  
		Map<String,String> heders= res.headers();
		node.setResponseHeader(heders);
		node.setHtml(res.body());
		handlerEvent(node,HandlerEventEnum.SEND_AFTER); 
		return res;
	}
	/**
	 * 璇诲彇鑺傜偣鏁版嵁
	 * @param node
	 * @throws Exception 
	 */
	public static void read(ConverterData node) throws  Exception{ 
		if(node.getConverterNode().getKey()!=null){
			String key=node.getConverterNode().getKey().getKey(); 
			key=StringTemplateUtils.render(key , node.getData()); 
			node.setKey(key);
			node.getData().put("$key", key);
			Object o=
			node.getConverterNode()
			.getApplicationContextConverter().getCache().get(key);
			if(o!=null){
				return;
			}
		}
		Response res=connection(node); 
		node.setHtml(res.body()); 
		handlerEvent (node,HandlerEventEnum.DATA_HANDLE_BEFORE);
		Map<String,Object> data=converterData(node,node.getHtml(),node.getConverterNode().getConverterModel());
		for(String item:data.keySet()){
			node.getData().put(item,data.get(item));
		}
		handlerEvent (node,HandlerEventEnum.DATA_HANDLE_AFTER); 
		for(ConverterNode model: node.getConverterNode().getChilds()){ 
			analyticalParent(model,node);
		} 
		if(node.getKey()!=null){
			node.getConverterNode()
			.getApplicationContextConverter().getCache().set(
					node.getKey(), 1, node.getConverterNode().getKey().getTime());
			
		} 
 
	}
}
