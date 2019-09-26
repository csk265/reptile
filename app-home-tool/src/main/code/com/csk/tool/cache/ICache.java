package com.csk.tool.cache;

public interface ICache {
	public  Object get(String key) ;
	public  <T> T getT(String key) ;
	public  void set(String key, Object value) ;
	public  void set(final String key, Object value, long millSeconds);
    public  boolean isExist(String key) ;
    public void delete(String key);
}
