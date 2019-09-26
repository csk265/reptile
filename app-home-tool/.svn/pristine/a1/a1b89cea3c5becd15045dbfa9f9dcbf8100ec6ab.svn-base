package com.csk.tool.single;

import java.util.HashMap;
import java.util.Map;

public class SingleUtil {	
	private static Map<String,ISingle> singleData=new HashMap<String,ISingle>();
	@SuppressWarnings("unchecked")
	public static <T extends  ISingle<T>> T get(Class<T> classes){
		if(!singleData.containsKey(classes.getName())){
			try {
				singleData.put(classes.getName(), classes.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}catch(Throwable  e){
				
			}
		}
		return (T) singleData.get(classes.getName());
	}
}
