package com.csk.collection.reptile.handler.impl;

import com.csk.collection.reptile.handler.IHandler;
import com.csk.tool.single.SingleUtil;
import com.csk.tool.string.StringMatcherUtil;
import com.csk.tool.xml.Node;
 
public class RegularHandler implements IHandler {
	StringMatcherUtil stringMatcherUtil=  SingleUtil.get(StringMatcherUtil.class);
	 
	private String match;
	@Override
	public Object Handler(Object o) { 
		return stringMatcherUtil.find(o.toString(), match);
	}

	@Override
	public void init(Node node) { 
		match= node.findNodeValue("value"); 
	}

}
