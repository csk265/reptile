package com.csk.collection.reptile;

public class ConverterKey {
 
	private String key;
	private Long time;  
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Long getTime() {
		return time==null?0:time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
}
