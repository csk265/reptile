package com.csk.collection.reptile.handlerevent;

import com.csk.collection.reptile.ConverterData;
import com.csk.collection.reptile.handlerevent.enums.HandlerEventEnum;
import com.csk.tool.xml.Node;
 
public interface IHandlerEvent {
	
	
	public void handlerEvent(ConverterData data);
	/**
	 * 初始化
	 * @param node
	 */
	public void init(Node node);
	public HandlerEventEnum getHandlerEventEnum();
}
