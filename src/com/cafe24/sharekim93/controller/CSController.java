package com.cafe24.sharekim93.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.board.cs.CSBoardAction;
import com.cafe24.sharekim93.board.cs.MailAction;
@WebServlet("*.cs")
public class CSController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { execute(req, resp); }
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { execute(request,response); }
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURL = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/"));
		System.out.println(requestURL);
		String path = "/WEB-INF/view/cs/mail.jsp";
		
		CSBoardAction comm = null;
		if(requestURL.equals("/index.cs")) {path = "/WEB-INF/view/cs/index.jsp";}
		else if(requestURL.equals("/mail.cs")) {path = "/WEB-INF/view/cs/mail.jsp";}
		else if(requestURL.equals("/sendmail.cs")) {comm=new MailAction();comm.execute(request, response);return;}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
