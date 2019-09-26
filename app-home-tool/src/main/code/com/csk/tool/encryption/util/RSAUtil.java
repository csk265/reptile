package com.csk.tool.encryption.util;
import sun.misc.*;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator; 
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher; 
import com.csk.tool.encryption.model.RSAModel;
import com.csk.tool.exception.ToolBusinessException;
public class RSAUtil { 
	/**
	 * 加密
	 * @param content
	 * @param key
	 * @return
	 */
	@SuppressWarnings("restriction")
	public String encrypt(String content,Key key){
	 
	      try {
			  Cipher cipher = Cipher.getInstance("RSA");  
	    	  cipher.init(Cipher.ENCRYPT_MODE, key);
	    	  byte[] b = content.getBytes();   
	          byte[] b1 = cipher.doFinal(b);   
			  BASE64Encoder encoder = new BASE64Encoder();  
	          return encoder.encode(b1);  
		} catch (Exception e) {
			e.printStackTrace();
			throw new ToolBusinessException();
		}  
	}
	 /** 
     * 解密算法 
     * @param cryptograph    密文 
     * @return 
     * @throws Exception 
     */  
    @SuppressWarnings("restriction")
	public static String decrypt(String cryptograph,Key key) throws Exception {     
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, key);  
        BASE64Decoder decoder = new BASE64Decoder();  
        byte[] b1 = decoder.decodeBuffer(cryptograph);   
        byte[] b = cipher.doFinal(b1);  
        return new String(b);  
    }  
	  /**
     * 得到公钥
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    @SuppressWarnings("restriction")
	public static PublicKey getPublicKey(String key) throws Exception {
          byte[] keyBytes;
          keyBytes = (new BASE64Decoder()).decodeBuffer(key); 
          X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
          PublicKey publicKey = keyFactory.generatePublic(keySpec);
          return publicKey;
    }
    /**
     * 得到私钥
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    @SuppressWarnings("restriction")
	public static PrivateKey getPrivateKey(String key) throws Exception {
          byte[] keyBytes;
          keyBytes = (new BASE64Decoder()).decodeBuffer(key); 
          PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
          PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
          return privateKey;
    }
    public RSAModel read(String publicString,String privateString){
    	RSAModel rsa=new RSAModel(publicString,privateString);
    	try {
			rsa.setPublicKey( getPublicKey(publicString));
			rsa.setPrivateKeyString(privateString);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ToolBusinessException();
		} 
    	return rsa;
    }
	/**
	 * 随机生成秘钥
	 * @return
	 */
	public RSAModel getRandRSA(){
		RSAModel rsa=new RSAModel();
		try {
			KeyPairGenerator keyPairGenerator =
					KeyPairGenerator.getInstance("RSA");
			 keyPairGenerator.initialize(512);
			 KeyPair keyPair = keyPairGenerator.generateKeyPair();  
			 Key publicKey = keyPair.getPublic();   
		     Key privateKey = keyPair.getPrivate();   
		     rsa.setPrivateKey(privateKey);
		     rsa.setPublicKey(publicKey);
		     rsa.setPublicKeyString(getKeyString(publicKey)); 
		     rsa.setPrivateKeyString(getKeyString(privateKey));
		     
		} catch ( Exception e) {
			  
			e.printStackTrace();
		} 
		return rsa;
	}
	@SuppressWarnings("restriction")
	private String getKeyString(Key key){
		 byte[] bytes =key.getEncoded();
		 return new BASE64Encoder().encode(bytes);
	}
}
