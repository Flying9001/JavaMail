
package com.ljq.utils;

import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/** 
 * 邮件发送器
 * 
 * @author lujunqiang 
 * Date: 2017年7月20日 下午2:47:35 
 *
 */
public class EmailSender {

	// 发送邮件的配置文件(properties)
	private final transient Properties props = System.getProperties();
	// 邮件服务器登录验证
	private transient EmailAuthenticator authenticator;
	// 邮箱会话(session)
	private transient Session session;
	
	
	public EmailSender(final String smtpHostName, final String username, final String passcode) {
		init(smtpHostName, username, passcode);
	}
	
	/**
	 * 初始化邮箱发送器
	 * 
	 * @param smtpHostName SMTP 邮件服务器地址
	 * @param username 发送邮件的用户名(地址)
	 * @param passcode 邮箱密码
	 * */
	private void init(String smtpHostName, String username, String passcode){
		// 初始化 properties
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHostName);
		// 验证
		authenticator = new EmailAuthenticator(username, passcode);
		// 创建 session
		session = Session.getInstance(props, authenticator);
	}
	
	/**
	 * 发送个人邮件
	 * 
	 * @param recipient 收件人邮箱地址
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * 
	 * @exception AddressException | MessagingException
	 * */
	public void send(String recipient, String subject, Object content){
		
		// 创建 mime 类型邮件
		final MimeMessage message = new MimeMessage(session);
		try {
			// 设置发信人
			message.setFrom(new InternetAddress(authenticator.getUsername()));
			// 设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
			// 设置主题
			message.setSubject(subject);
			// 设置邮件内容
			message.setContent(content.toString(), "text/html;charset=utf-8");
			// 发送邮件
			Transport.send(message);
			
			System.out.println("邮件发送成功!!!");
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 群发邮件
	 * 
	 * @param recipients 收件人们
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * 
	 * @exception AddressException | MessagingException
	 * */
	public void send(List<String> recipients, String subject, Object content){
	
		// 创建 mime 类型邮件
		final MimeMessage message = new MimeMessage(session);
		try {
			// 设置发信人
			message.setFrom(new InternetAddress(authenticator.getUsername()));
			// 设置收件人们
			final int num = recipients.size();
			InternetAddress[] addresses = new InternetAddress[num];
			for (int i = 0; i < num; i++) {
				addresses[i] = new InternetAddress(recipients.get(i));
			}
			message.setRecipients(RecipientType.TO, addresses);
			// 设置主题
			message.setSubject(subject);
			// 设置邮件内容
			message.setContent(content.toString(), "text/html;charset=utf-8");
			// 发送
			Transport.send(message);
			
			System.out.println("邮件群发成功！！！");
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	
}













