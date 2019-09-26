package com.csk.collection.reptile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import com.csk.collection.reptile.converter.model.ConverterModel;
import com.csk.collection.reptile.handlerevent.IHandlerEvent;
import com.csk.tool.cache.ICache;

/**
 * 转换模型
 * @author Administrator
 *
 */
public class ConverterNode {
	/**
	 * 子节点
	 */
	private  List<ConverterNode> childs=new ArrayList<ConverterNode>();
	/**
	 * 采集地址
	 */
	private String url;
	/**
	 * 父节点
	 */
	private ConverterNode parent;
 
	/**
	 * 数据转换器
	 */
	private List<ConverterModel> converterModel;
	/**
	 * 子节点转换器
	 */
	private ConverterModel childsConverterModel;
	/**
	 * 子节点数据转换器
	 */
	private List<ConverterModel> childsConverterModelData; 
	/**
	 * 数据过滤对象
	 */
	private List<IHandlerEvent> handlerEvent=new ArrayList<IHandlerEvent>();
	private ConverterKey key;
	
	
	public ConverterKey getKey() {
		return key;
	}
	public void setKey(ConverterKey key) {
		this.key = key;
	}
	private ApplicationContextConverter applicationContextConverter;
	
	public ApplicationContextConverter getApplicationContextConverter() {
		return applicationContextConverter;
	}
	public void setApplicationContextConverter(
			ApplicationContextConverter applicationContextConverter) {
		this.applicationContextConverter = applicationContextConverter;
		//进行无止境的 递归递归
		for(ConverterNode item:childs){
			item.setApplicationContextConverter(applicationContextConverter);
		}
	}
	public List<IHandlerEvent> getHandlerEvent() {
		return handlerEvent;
	}
	public void setHandlerEvent(List<IHandlerEvent> handlerEvent) {
		this.handlerEvent = handlerEvent;
	}
	public List<ConverterModel> getChildsConverterModelData() {
		return childsConverterModelData;
	}
	public void setChildsConverterModelData(
			List<ConverterModel> childsConverterModelData) {
		this.childsConverterModelData = childsConverterModelData;
	}
	public ConverterModel getChildsConverterModel() {
		return childsConverterModel;
	}
	public void setChildsConverterModel(ConverterModel childsConverterModel) {
		this.childsConverterModel = childsConverterModel;
	}
	public List<ConverterNode> getChilds() {
		return childs;
	}
	public void setChilds(List<ConverterNode> childs) {
		this.childs = childs;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ConverterNode getParent() {
		return parent;
	}
	public void setParent(ConverterNode parent) {
		this.parent = parent;
	}
 
	public List<ConverterModel> getConverterModel() {
		return converterModel;
	}
	public void setConverterModel(List<ConverterModel> converterModel) {
		this.converterModel = converterModel;
	}
	
	
}
