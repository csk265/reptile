package com.csk.tool.object;

import java.util.Map;

/**
 * 对象表达式
 * @author Administrator
 *
 */
public class ObjectExpression {
	
	/**
	 * 表达式获取数据
	 * @param o
	 * @param ext
	 * @return
	 */
	public static Object getValue(Map<?,?> o,String ext){
		if(o.containsKey(ext)){
			return o.get(ext);
		}else if( ext.indexOf(".")!=-1){
			String name=ext.substring(0,ext.indexOf(".")); 
			if(o.containsKey(name)){
				Object o1=o.get(name);
				if(o1!=null&&o1  instanceof Map ){
					o=(Map<?, ?>)o1; 
					ext=ext.substring(ext.indexOf(".")+1,ext.length());
					return getValue(o, ext);
				}
			} 
		} 
		return null;
	}
}
