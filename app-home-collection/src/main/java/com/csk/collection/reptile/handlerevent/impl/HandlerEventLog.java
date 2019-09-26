package com.csk.collection.reptile.handlerevent.impl;

import com.alibaba.fastjson.JSONObject;
import com.csk.collection.reptile.ConverterData;
import com.csk.collection.reptile.handlerevent.BaseHandlerEvent;
import com.csk.collection.reptile.handlerevent.IHandlerEvent;
import com.csk.tool.log.Log;
import com.csk.tool.single.SingleUtil;

public class HandlerEventLog extends BaseHandlerEvent implements IHandlerEvent {
	Log log=SingleUtil.get(Log.class);
	@Override
	public void handlerEvent(ConverterData data) {
//		log.info("html");
//		log.info(data.getHtml());
		log.info("param");
		for(String key:data.getData().keySet()){
			log.info(key+":"+JSONObject.toJSONString(data.getData().get(key)));
		}
	}

}
