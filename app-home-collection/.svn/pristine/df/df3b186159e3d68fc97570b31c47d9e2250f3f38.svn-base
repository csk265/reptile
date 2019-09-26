package com.csk.collection.reptile.handlerevent.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.csk.collection.reptile.ConverterData;
import com.csk.collection.reptile.exception.ReptileBusinessException;
import com.csk.collection.reptile.handlerevent.BaseHandlerEvent;
import com.csk.collection.reptile.handlerevent.IHandlerEvent;
import com.csk.tool.log.Log;
import com.csk.tool.object.ObjectExpression;
import com.csk.tool.single.SingleUtil;
import com.csk.tool.xml.Node;

/**
 * jdbc插入
 * @author Administrator
 *
 */
public class HandlerEventJDBC extends BaseHandlerEvent implements IHandlerEvent{ 
	Log log=SingleUtil.get(Log.class);
	public String getSql(Map<String,Object> data){
		StringBuffer sql=new StringBuffer("(");
		StringBuffer sql1=new StringBuffer("values(");
		int count=0;
		for(String key:data.keySet()){
			sql.append("`"+key+"`");
			sql1.append("?");
			 
			if(!(count==data.size()-1)){
				sql.append(",");
				sql1.append(",");
			}
			count++;
		}
		sql.append(")");
		sql1.append(")");
		return sql.toString() +
				sql1.toString()
				;
	}
	@Override
	public void handlerEvent(ConverterData data) {
		Node jdbc=node.find("jdbc");
		Connection conn=null;
		if(jdbc!=null){
			conn=getConnection(jdbc);
		}
		Node dataNode=node.find("table");
		if(dataNode!=null){
			String tableName=dataNode.attr("name");
			Map<String,Object> param=data.getData();
			Map<String,Object> sqlData=new LinkedHashMap<>();
			dataNode.findList("field").forEach(field->{
				String name=field.findNodeValue("name");
				String type=field.findNodeValue("type");
				String value=field.findNodeValue("value");
				if("".equals(type)){
					sqlData.put(name, ObjectExpression.getValue(param, value)); 
				} 
			});
			String sql="insert into "+tableName+getSql(sqlData); 
			PreparedStatement ps= null;
			try {
				  ps= conn.prepareStatement(sql); 
				  int i=0;
				  for(String key:sqlData.keySet()){
					  i++;
					  ps.setObject(i, sqlData.get(key)); 
				  }
				 ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(ps!=null){
					try {
						ps.close();
					} catch (SQLException e) {
						log.error(e, "sqlClose");
						e.printStackTrace();
					}
				} 
			}
			
			
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 
		
	}
	private Connection getConnection(Node node){
		String url=node.findNodeValue("url");
		String name=node.findNodeValue("name");
		String password=node.findNodeValue("password");
		String driverClassName=node.findNodeValue("driver-class-name");
		try {
			Class.forName(driverClassName);
			Connection conn = DriverManager.getConnection(url,name,password);  
			return conn;
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		} catch (SQLException e) { 
			e.printStackTrace();
		} 
		throw new ReptileBusinessException("open connection error"); 
	}
	
	public static void main(String ...args){
		Map<String,Object> data=new HashMap<String,Object>();
		List<Integer> p=new ArrayList<Integer>();
		p.add(123);
		p.add(345); 
		data.put("u", 123);
		data.put("a", p);
			System.out.println(  
		token(data));
		
	}
	public static String token(Map<String,Object> data){
		data=ksort(data);
		StringBuffer token=new StringBuffer();
		for(String key:data.keySet()){
			String value=null;
			if(data.get(key).getClass().isArray()){
				value=JSONObject.toJSONString(data.get(key));
			}else{
				value=data.get(key).toString();
			}
			token.append(key+"="+value);
		}
		return token.toString();
	}
	public static Map<String,Object> ksort(Map<String,Object> item){ 
		String[] key = new String[item.size()];
		int index = 0;
		for (String k : item.keySet()) {
		 key[index] = k;
		 index++;
		}
		Arrays.sort(key); 
		Map<String,Object> result=new LinkedHashMap<String,Object>();
		for(int i=0;i<key.length;i++){
			result.put(key[i], item.get(key[i]));
		}
		return result;
	}
	

}
