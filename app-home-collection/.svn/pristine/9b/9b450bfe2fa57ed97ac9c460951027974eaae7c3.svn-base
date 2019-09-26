package com.csk.collection.reptile.handlerevent.impl;

import com.alibaba.fastjson.JSONObject;
import com.csk.collection.reptile.ConverterData;
import com.csk.collection.reptile.handlerevent.BaseHandlerEvent;
import com.csk.collection.reptile.handlerevent.IHandlerEvent;

public class HandlerEventJSON extends BaseHandlerEvent implements IHandlerEvent {

	@Override
	public void handlerEvent(ConverterData data) {
		String path= node.findNodeValue("path");
	 
		System.out.println(  
		JSONObject.toJSONString(data.getData().get(path)));
	}
	
}
