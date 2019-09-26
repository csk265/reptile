package com.csk.tool.xml;
 
import java.io.BufferedReader;
import java.io.File; 
import java.io.FileReader; 
import java.io.StringReader; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
 

public class Node {

	private List<Node> children;
	private Map<String,String> attribute;
	private String tagName;
	private String value; 
	private Node parent;
	public static void main(String...arga) throws Exception{ }
	public static Node readNode(String path) throws  Exception{
		File file=new File(path);
		if(file.exists()){
			FileReader fileRead=new FileReader(file);
			BufferedReader br=new BufferedReader(fileRead);
			String tempString=null;
			StringBuffer sb=new StringBuffer();
			while ((tempString = br.readLine()) != null) {  
				sb.append(tempString);
			} 
			return toNode(sb.toString());
		}
		return null;
	}
	public static Node toNode(String body) throws Exception  {
		StringReader sr = new StringReader(body); 
		InputSource is = new InputSource(sr); 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder=factory.newDocumentBuilder(); 
		Document doc = builder.parse(is);		
		return toNode(doc.getDocumentElement());
		 
	}
	private static Node toNode(Element element){
		Node node=new Node(element.getTagName(),null);
		for(int i=0;i<element.getAttributes().getLength();i++){ 
			node.addAttribute(
					element.getAttributes().item(i).getNodeName(), 	
					element.getAttributes().item(i).getNodeValue() ); 
		}
		boolean check=false;
		for(int i=0;i<element.getChildNodes().getLength();i++){ 
			if( element.getChildNodes().item(i).getNodeType()==1){
				check=true;
				Node n=toNode((Element)element.getChildNodes().item(i));
				n.setParent(node);
				node.add(n);
			}
		} 
		if(!check){ 
			node.setValue(element.getTextContent());
		} 
		return node;
	}
	public Node getParents(){
		Node n=getParent();
		if(n==null){
			return n;
		}
		while(n.getParent()!=null){
			n=n.getParent();	
		}
		return n;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node(String tagName,String value){
		this.tagName=tagName;
		this.value=value;
	}
	public Node (String tagName,String value,Node ...node){
		this.tagName=tagName;
		this.value=value;
		if(node.length>0){ 
			for(Node item:node){
				if(item!=null)
				getChildren().add(item);
			}
		}
	}
	public Node addAttribute(String name,String value){
		getAttribute().put(name, value);
		return this;
	}
	public Node add(Node node){
		getChildren().add(node);
		return this;
	}
	public Node (String tagName,String value,String ...args){
		this.tagName=tagName;
		this.value=value;
		if(args.length>0&&args.length%2==0){
			getAttribute();
			for(int i=0;i<args.length;i+=2){
				attribute.put(args[i], args[i+1]);
			}
		}
	}
	public List<Node> findList(String tagName){
		List<Node> result=new ArrayList<Node>();
		if(children==null)
			return result;
		for(Node item:children){
			if(tagName.equals(item.getTagName()))
				result.add(item);
		}
		return result;
	}
	public Node find(String tagName){
		if(children==null)
			return null;
		for(Node item:children){
			if(tagName.equals(item.getTagName()))
				return item;
		}
		return null;
	}
	public String toXml(){
		StringBuilder sb=new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");	
		toXml(sb,"");
		return sb.toString();
	} 
	private void toXml(StringBuilder sb,String tab){
		sb.append(tab+"<");
		sb.append(tagName);
		if(attribute!=null&&attribute.size()>0){
			for(String name:attribute.keySet()){
				sb.append(" "+name+"=\""+attribute.get(name)+"\"");
			}
		}
		sb.append(">");
		if(children!=null&&children.size()>0){
			sb.append("\n");
			for(Node item:children){
				item.toXml(sb,tab+"\t");
			}
			sb.append(tab); 
		}else if(value!=null&&!value.equals("")){
			sb.append(value);
		}
		sb.append("</");
		sb.append(tagName);
		sb.append(">\n");
		
	}
	public List<Node> getChildren() {
		if(children==null)
			children=new ArrayList<Node>();	
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
	public Map<String, String> getAttribute() {
		if(attribute==null){
			attribute=new HashMap<String,String>();
		}
		return attribute;
	}
	public String attr(String name){
		if(attribute==null){
			return "";
		}
		if(attribute.get(name)!=null){
			return attribute.get(name);
		}
		return "";
	}
	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String findNodeValue(String string) {
		Node n=find(string);
		if(n!=null){
			return n.value;
		}
		return "";
	}
	

}
