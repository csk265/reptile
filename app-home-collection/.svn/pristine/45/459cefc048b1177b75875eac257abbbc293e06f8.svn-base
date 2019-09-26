package com.csk.collection.reptile.handlerevent;

import com.csk.collection.reptile.exception.ReptileBusinessException;
import com.csk.collection.reptile.handlerevent.enums.HandlerEventEnum;
import com.csk.tool.xml.Node;

public abstract class BaseHandlerEvent implements IHandlerEvent{
	protected Node node;
	protected HandlerEventEnum handlerEventEnum;
	
	
	public void init(Node node){
		this.node=node;
		String type=node.findNodeValue("handler-event-type");
		HandlerEventEnum[]values= HandlerEventEnum.values();
		for(HandlerEventEnum item:values){
			if(item.getType().equals(type)){
				this.handlerEventEnum=item;
				return;
			}
		}
		throw new ReptileBusinessException("not find   "+ type+" enum"); 
	}
	public HandlerEventEnum getHandlerEventEnum(){
		return handlerEventEnum;
	}
	
}
