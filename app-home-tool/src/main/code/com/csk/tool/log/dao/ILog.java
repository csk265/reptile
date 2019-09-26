package com.csk.tool.log.dao;

public interface ILog {
	
	public void info(String ...args);
	public void error(String ...args);
	public void error(Throwable e,String ...args);
}
