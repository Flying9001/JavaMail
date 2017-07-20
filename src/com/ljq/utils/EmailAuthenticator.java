
package com.ljq.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/** 
 * 服务器邮箱登录验证
 * 
 * @author lujunqiang 
 * Date: 2017年7月20日 下午2:38:22 
 *
 */
public class EmailAuthenticator extends Authenticator{
	
	private String username;
	private String passcode;
	
	
	public EmailAuthenticator(String username, String passcode) {
		this.username = username;
		this.passcode = passcode;
	}

	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, passcode);
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	
	
	
	
	
	

}
