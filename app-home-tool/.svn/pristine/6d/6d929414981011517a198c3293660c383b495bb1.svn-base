package com.csk.tool.log.dao;


import java.util.ArrayList;
import java.util.List;

public abstract class BaseLog implements ILog {
	private List<ILog> logAll=new ArrayList<ILog>(); 
	
	public void add(ILog log){
		logAll.add(log);
	}
	public void remvoer(ILog log){
		logAll.remove(log);
	}
	
	public void info(String... args) {
		logAll.forEach(m->{
			m.info(args);
		});
	}

	public void error(String... args) {
		logAll.forEach(m->{
			m.error(args);
		});
	}

	public void error(Throwable e, String... args) {
		logAll.forEach(m->{
			m.error(e,args);
		});
	}

}
