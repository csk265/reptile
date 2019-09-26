package com.csk.tool.cache.impl;

import java.io.File;
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream; 
import java.util.Date;  
import com.csk.tool.cache.BaseCache;
import com.csk.tool.cache.ICache; 
public class FileCacheClient extends BaseCache implements ICache {
	
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public FileCacheClient(){
		//设置文件缓存路径
		filePath=this.getClass().getResource("/").getFile()+"cache/"; 
	}
	public FileCacheClient(String path){
		if(path!=null){
			filePath=path;
		}else{
			filePath=this.getClass().getResource("/").getFile()+"cache/"; 
		}
	}
	public static void main(String ...args){
		FileCacheClient f=new FileCacheClient();
		System.out.println( 
		f.get("abc") );
	}
	private String getKey(String key){
		return key;
	}
	@Override
	public Object get(String key) {
		key=getKey(key);
		File f=new File(filePath+key); 
		if(f.exists()){  
			try {
				 Long time2=f.lastModified();
				 Long time3=new Date().getTime();
				if(time2 >=time3){
					FileInputStream fis= new FileInputStream(f);
					ObjectInputStream ois = new ObjectInputStream(fis); 
					Object o=ois.readObject();
					ois.close();
					fis.close();
					return o;
				}else{ 
					f.delete();
				}  
			} catch (Exception e) { 
			}
			 
			 
		}
		return null;
	}

	@Override
	public void set(String key, Object value) {
		set(key,value,0);
	}

	@Override
	public void set(String key, Object value, long millSeconds) {
		key=getKey(key);
		FileOutputStream fos;
		try { 
			File file=new File(filePath+key);
			if(file.getParentFile()!=null&&!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(value);
			oos.close();
			fos.close(); 
			long time=new Date().getTime()+(millSeconds==0?(( 365*24l*60*60*1000l)):millSeconds); 
			file.setLastModified(time);
			
		} catch (Exception e) { 
			e.printStackTrace();
		}		
	 
		
	}
	@Override
	public void delete(String key) {
		key=getKey(key);
		File file=new File(filePath+key);
		if(file.exists()){
			file.delete();
		}
	}

}
