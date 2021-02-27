package com.cafe24.sharekim93.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.service.MypageService;

public class WithdrawAction implements MemberAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid=String.valueOf(request.getSession().getAttribute("id"));
		String mpass = request.getParameter("mpass");
		
		int result = new MypageService().deleteMember(mid, mpass);
		if(result >0){
			request.getSession().invalidate();
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("login_id")){cookie.setMaxAge(0);response.addCookie(cookie);}
			}
			}
		request.setAttribute("withdraw", result);
	}
	
}
