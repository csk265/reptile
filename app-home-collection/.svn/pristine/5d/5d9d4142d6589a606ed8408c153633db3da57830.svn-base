package com.csk.collection.reptile.handler.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.csk.collection.reptile.handler.IHandler;
import com.csk.tool.xml.Node;

public class ReplaceHandler implements  IHandler  {
	Map<String,String> replaces=new LinkedHashMap<String,String>();
	@Override
	public Object Handler(Object o) {
		for(String key:replaces.keySet()){
			o=o.toString().replace(key, replaces.get(key));
		}
		return o;
	}
	@Override
	public void init(Node node) {
		List<Node> nodes=node.findList("replace");
		for(Node n:nodes){
			replaces.put(n.attr("name"), 	n.attr("value"));  
		}
	}

}
