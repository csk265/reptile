package com.csk.tool.notice.email;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtil {
	public static void main(String ...args) throws Exception{
		send("测试","测试测试重 ");
	}
	public static void send(String title,String body) throws Exception{ 
		  Properties pro = new Properties(); 
		  pro.put("mail.smtp.host", "smtp.163.com"); 
		  pro.put("mail.smtp.auth", "true"); 
		  MailSSLSocketFactory sf = null; 
		  sf = new MailSSLSocketFactory(); 
		  // 设置信任所有的主机 
		  sf.setTrustAllHosts(true); 
		  pro.put("mail.smtp.ssl.enable", "true"); 
		  pro.put("mail.smtp.ssl.socketFactory", sf);  
		  MailAuthenticator authenticator = new MailAuthenticator("csksmssend", 
				    "qq1498408339aa"); 
		  Session session = Session.getInstance(pro, authenticator); 
				  // 根据Session 构建邮件信息 
		  Message message = new MimeMessage(session); 
				  // 创建邮件发送者地址 
		  Address from = new InternetAddress("csksmssend@163.com");
		  message.setFrom(from);  
		  Address[] to = InternetAddress.parse("csksms@163.com");
		  message.setRecipients(Message.RecipientType.TO, to); 
		  MimeMultipart mimeMultiPart = new MimeMultipart(); 
		  BodyPart bodyPart = new MimeBodyPart();  
		  message.setSubject(title);
		  bodyPart.setContent(body, "text/html;charset=utf-8"); 
		  mimeMultiPart.addBodyPart(bodyPart);  
		  message.setContent(mimeMultiPart); 
		  message.setSentDate(new Date());  
		  message.saveChanges();  
		  Transport.send(message); 
	}
}
