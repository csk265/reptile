package com.csk.tool.cos.object;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

public class COSUtil {
	private String secretId;
	private String secretKey; 
	private String defaultRegionName;
	private String defaultBucketName; 
	public String getDefaultRegionName() {
		return defaultRegionName;
	}
	public void setDefaultRegionName(String defaultRegionName) {
		this.defaultRegionName = defaultRegionName;
	}
	public String getSecretId() {
		return secretId;
	}
	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public COSUtil(){
		this("AKIDXMxJPFl9D1ZKp44nlUyB9rEROPCjl6wW","UiiTyDSegRoCWaNsPgla9S2EPM8esvlD");
		this.defaultRegionName= "ap-guangzhou";
		this.defaultBucketName="2019-1259149877";
	}
	public COSUtil(String secretId,String secretKey){
		this.secretId=secretId;
		this.secretKey=secretKey;
	} 
	public COSClient getClient(String regionName){
		COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
		Region region = new Region(regionName!=null?regionName:defaultRegionName);
		ClientConfig clientConfig = new ClientConfig(region); 
		return  new COSClient(cred, clientConfig); 
	}
	public boolean putObject(String  key,Object obj){
		return putObject(null,null,key,obj);
	}
	public boolean putObject(String bucketName,String  key,Object obj){
		return putObject(null,bucketName,key,obj);
	}
	private String getBody(Object obj){
		if(obj!=null){ 
			if(obj.getClass().getName().equals("java.lang.String")){ 
			
				return obj.toString();
			}else{
				return JSONObject.toJSONString(obj);	
			}
	 	}else{
			return "";
		}
		
	}
	public boolean putObject(String regionName,String bucketName,String  key,Object obj){
		COSClient client=getClient(null);
		String body=getBody(obj);
		PutObjectResult result=client.putObject(
				bucketName==null? defaultBucketName:bucketName, key, body);	
		return result!=null;
	}
}
