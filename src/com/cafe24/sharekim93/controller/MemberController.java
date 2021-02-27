package com.cafe24.sharekim93.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.member.CheckIDAction;
import com.cafe24.sharekim93.member.EditInfoAction;
import com.cafe24.sharekim93.member.EditPassAction;
import com.cafe24.sharekim93.member.GoEditPage;
import com.cafe24.sharekim93.member.JoinAction;
import com.cafe24.sharekim93.member.LoginAction;
import com.cafe24.sharekim93.member.LogoutAction;
import com.cafe24.sharekim93.member.MemberAction;
import com.cafe24.sharekim93.member.MypageAction;
import com.cafe24.sharekim93.member.WithdrawAction;

@WebServlet("*.do")
public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURL = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/"));
		System.out.println(requestURL);
		String path = "/WEB-INF/member/login.jsp";
		
		MemberAction comm= null;
		if(requestURL.equals("/login.do")) {comm=new LoginAction(); comm.execute(request, response);
											path = "/WEB-INF/member/mypage.jsp";}
		else if(requestURL.equals("/logout.do")) {comm = new LogoutAction(); comm.execute(request, response);}
		else if(requestURL.equals("/join_agree.do")) 	 {path="/WEB-INF/member/join_agree.jsp";}
		else if(requestURL.equals("/join.do")) 	 {path="/WEB-INF/member/join.jsp";}
		else if(requestURL.equals("/checkID.do")) {comm = new CheckIDAction(); comm.execute(request, response);return;}
		else if(requestURL.equals("/joinAction.do")) {comm = new JoinAction(); comm.execute(request, response);
													  path="/WEB-INF/member/join_com.jsp";}	
		else if(requestURL.equals("/mypage.do")) {comm = new MypageAction();comm.execute(request, response);
												  path="/WEB-INF/member/mypage.jsp";}
		else if(requestURL.equals("/goEditPage.do")) {comm = new GoEditPage(); comm.execute(request, response);
														  path="/WEB-INF/member/mypage_edit.jsp";}
		else if(requestURL.equals("/editInfoAction.do")) {comm = new EditInfoAction(); comm.execute(request, response);
															path="/WEB-INF/member/mypage.jsp";}
		else if(requestURL.equals("/goPassPage.do")) {path="/WEB-INF/member/mypage_pass.jsp";}
		else if(requestURL.equals("/editPassAction.do")) {comm = new EditPassAction(); comm.execute(request, response);
														  path="/WEB-INF/member/mypage.jsp";}
		else if(requestURL.equals("/WithdrawAction.do")) {comm = new WithdrawAction(); comm.execute(request, response);
														  path="/WEB-INF/member/login.jsp";}
		request.getRequestDispatcher(path).forward(request, response);
	}
}
