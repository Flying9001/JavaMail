
package com.ljq.test;

import java.util.ArrayList;
import java.util.List;

import com.ljq.utils.EmailSender;

/** 
 * @author lujunqiang 
 * Date: 2017年7月20日 下午3:58:32 
 *
 */
public class EmailSendTest {
	
	
	public static void main(String[] args) {
		
		// 创建邮件发送器	
		EmailSender emailSender = new EmailSender("smtp.163.com", "flying9001@163.com", "********");
		// 设置收件人
//		String recipient = "rede.lu.5945@gmail.com";
		// 发送邮件
//		emailSender.send(recipient, "Subject:Email send by java", "Content:test");
		
		// 邮件群发
		List<String> recipients = new ArrayList<>();
		recipients.add("rede.lu.5945@gmail.com");
		recipients.add("flying9001@qq.com");
		emailSender.send(recipients, "Subject: Mass Mail send by java Test", "Content: Mass Mail Test");
		
	}
	

}
