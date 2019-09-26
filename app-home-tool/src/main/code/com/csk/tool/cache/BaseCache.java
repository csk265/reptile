package com.csk.tool.cache;

public abstract class BaseCache implements ICache {
	  public  boolean isExist(String key) {
	        return get(key)!=null;
	  }
	  public  <T> T getT(String key) {
		  //123
	        Object obj = get(key);
	        return obj == null ? null : (T) obj;
	  } 
}
