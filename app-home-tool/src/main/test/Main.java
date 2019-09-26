import com.csk.tool.xml.COSUtil;

public class Main { 
	public static void main(String ...args){
		COSUtil cos=new COSUtil();
		cos.putObject( "abc/asd.txt", "0123");
//		String secretId = "AKIDXMxJPFl9D1ZKp44nlUyB9rEROPCjl6wW";
//		String secretKey = "UiiTyDSegRoCWaNsPgla9S2E	PM8esvlD";
//		String bucketName="2019-1259149877";
//		String key="test";
//		String content="123312";
//		COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
//		Region region = new Region("ap-guangzhou");
//		ClientConfig clientConfig = new ClientConfig(region); 
//		COSClient cosClient = new COSClient(cred, clientConfig);
//		COSObject obj=cosClient.getObject(bucketName, key);
//		obj.getObjectMetadata().
//		System.out.println(obj.getObjectMetadata().getETag());
		//PutObjectResult req=cosClient.putObject(bucketName, key, content);	
		
		
	}
}
