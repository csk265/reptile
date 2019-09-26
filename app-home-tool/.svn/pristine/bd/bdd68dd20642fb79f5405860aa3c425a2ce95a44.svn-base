package com.csk.tool.log;


import com.csk.tool.log.dao.BaseLog;
import com.csk.tool.log.dao.ILog;
import com.csk.tool.single.ISingle;

public class Log extends BaseLog implements ILog,ISingle<Log>{
	//是否打印日志
	private boolean isLog=true; 
	public Log(){
		
	}
	public Log(ILog... log){
		for(ILog item:log){
			this.add(item);
		}
	}
	public boolean isLog() {
		return isLog;
	}

	public void setLog(boolean isLog) {
		this.isLog = isLog;
	}

	public void info(String... args) {
		if(isLog){
			super.info(args);
		}
	}

	public void error(String... args) { 
		if(isLog){
			super.error(args);
		}
	}

	public void error(Throwable e, String... args) {
		if(isLog){ 
			super.error(e,args);
		}
	}
	@Override
	public Log get() { 
		return this;
	}
	
}
