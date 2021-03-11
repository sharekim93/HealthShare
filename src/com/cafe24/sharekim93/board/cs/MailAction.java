package com.cafe24.sharekim93.board.cs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.util.MailNaver;

public class MailAction implements CSBoardAction{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String from = request.getParameter("from");
		MailNaver mail = new MailNaver();
		mail.setSubject("[From 포트폴리오]"+title);
		mail.setContent("회신 받을 이메일 주소 : "+from +"<br/><br/>"+content);
		mail.setTo("sksyag@naver.com");
		int result = mail.sendMail();
		
		out.print(result);
	}
}
