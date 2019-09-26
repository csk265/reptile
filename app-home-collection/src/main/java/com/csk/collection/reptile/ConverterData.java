package com.csk.collection.reptile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csk.collection.reptile.handlerevent.IHandlerEvent;



public class ConverterData {
	private  List<ConverterData> childs=new ArrayList<ConverterData>(); 
	private String url;
	private Map<String,Object> data=new HashMap<String,Object>();
 
	private Map<String,String> requestHeader=new HashMap<String,String>(); 
	private Map<String,String> responseHeader=new HashMap<String,String>(); 
	private ConverterData parent;
	private ConverterNode converterNode;
	private String html;
	private String key;
	
 
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Map<String, String> getResponseHeader() {
		return responseHeader;
	}
	public void setResponseHeader(Map<String, String> responseHeader) {
		this.responseHeader = responseHeader;
	}
	public Map<String, String> getRequestHeader() {
		return requestHeader;
	}
	public void setRequestHeader(Map<String, String> requestHeader) {
		this.requestHeader = requestHeader;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public List<ConverterData> getChilds() {
		return childs;
	}
	public void setChilds(List<ConverterData> childs) {
		this.childs = childs;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public ConverterData getParent() {
		return parent;
	}
	public void setParent(ConverterData parent) {
		this.parent = parent;
	}
	public ConverterNode getConverterNode() {
		return converterNode;
	}
	public void setConverterNode(ConverterNode converterNode) {
		this.converterNode = converterNode;
	}
	
}
