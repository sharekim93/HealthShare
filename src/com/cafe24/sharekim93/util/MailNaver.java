package com.cafe24.sharekim93.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//1. Maven repository에서 다운로드
// https://mvnrepository.com/artifact/javax.mail/mail/1.4.7
//2. jar파일을 src 폴더에 넣고 BuildPath에 add함
//3. naver에 가서 SMTP를 설정해야함
/* POP 서버명 : pop.naver.comSMTP
	서버명 : smtp.naver.com
	POP 포트 : 995, 보안연결(SSL) 
	필요SMTP 포트 : 465, 보안 연결(SSL) 
	필요아이디 : sharekim93//@naver.com까지 적기
	비밀번호 : 네이버 로그인 비밀번호
*/

public class MailNaver {
	String subject;
	String content;
	String host;
	String from;
	String user;
	String password;
	String to;
	int result;
	public MailNaver(){
		subject  = "메일제목";
		content  = "메일내용";
		host	 = "smtp.naver.com";
		from	 = "sharekim93@naver.com";
		user 	 = "sharekim93@naver.com";//@naver.com까지 적기
		password = "sharekim93!";
		to 		 = "sksyag@naver.com";
		result	 = -1;
	}
	public int sendMail() throws UnsupportedEncodingException {		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.naver.com");
		
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user,password);
			}
		});
		try {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setContent(content,"text/html;charset=euc-kr");
		Transport.send(message);
		result=1;
		}
		catch(AddressException e) { e.printStackTrace(); }
		catch (MessagingException e) { e.printStackTrace(); }
		
		return result;
	}
	
	public void setSubject(String subject) { this.subject = subject; }
	public void setContent(String content) { this.content = content; }
	public void setTo(String to) {this.to = to;}
	public void setFrom(String from) { this.from = from; }
	
	
}
