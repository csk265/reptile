package com.csk.tool.log.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.csk.tool.log.dao.ILog;

public class ConsoleLog implements ILog{
	
	public SimpleDateFormat dateFrom; 
	public ConsoleLog(){
		this("yyyy-MM-dd HH:mm:ss---->");
	}
	public ConsoleLog(String dateFromStr){
		if(dateFromStr!=null){
			dateFrom=new SimpleDateFormat(dateFromStr);
		}
	}
	
	
	public void info(String... args) {
		for(String item:args){
			System.out.println(
					dateFrom.format(new Date())+item
					);
		}
	}

	public void error(String... args) {
		for(String item:args){
			System.err.println(
					dateFrom.format(new Date())+item
					);
		}
	}

	public void error(Throwable e, String... args) {
		System.err.println(	dateFrom.format(new Date())+e.getLocalizedMessage());
		for(String item:args){
			System.err.println(
					dateFrom.format(new Date())+item
					);
		}
	}
	
}
