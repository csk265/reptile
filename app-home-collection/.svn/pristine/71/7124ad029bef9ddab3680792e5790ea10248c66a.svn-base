package com.csk.collection.reptile.handlerevent.enums;

public enum HandlerEventEnum {
	/**
	 * 顶级节点	运行 发送前 ->发送后 -> 数据处理前->数据处理后
	 * 		子及节点
	 * 			运行 分析子节点之前->发送前->发送后->数据处理前->数据处理后->分析子节点之后
	 * 
	 * 
	 */
	//发送前
	SEND_BEFORE("SEND_BEFORE"),
	//发送后
	SEND_AFTER("SEND_AFTER"),
	//数据处理前
	DATA_HANDLE_BEFORE("DATA_HANDLE_BEFORE"),
	//数据处理后
	DATA_HANDLE_AFTER("DATA_HANDLE_AFTER"),
	//分析子节点之前
	ANALYSISI_NODE_BEFORE("ANALYSISI_NODE_BEFORE"),
	//分析子节点之后
	ANALYSISI_NODE_AFTER("ANALYSISI_NODE_AFTER") 
	;
	  
	private String type; 
	HandlerEventEnum(String type){
		this.type=type;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
