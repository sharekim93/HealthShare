package com.cafe24.sharekim93.member;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Member;
import com.cafe24.sharekim93.service.LoginService;
import com.cafe24.sharekim93.service.MypageService;

public class LoginAction implements MemberAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String	mid = request.getParameter("mid");
		String	mpass = request.getParameter("mpass");
		String	chkbox = request.getParameter("chkbox");
		String kakaoid = (String) request.getAttribute("kakaoid");		
		int result =-1; int kakaoresult =-1;
		if(kakaoid!=null) {mid = new LoginService().Login(kakaoid);}
		else {result = new LoginService().Login(mid, mpass);}
		
		if(mid!=""&&mid!=null) {kakaoresult=1;result=1;}
			if(result>0){
				request.getSession().setAttribute("id", mid);
				if(chkbox!=null){
					Cookie cookie = new Cookie("login_id",mid);
					cookie.setPath("./member");
					response.addCookie(cookie);
					}
				else {
					Cookie[] cookies = request.getCookies();
					for(Cookie cookie:cookies){
						if(cookie.getName().equals("login_id")){cookie.setMaxAge(0);response.addCookie(cookie);}
					}
				}
				Member member = new MypageService().getMember(mid);
				request.setAttribute("member",member);
			}
			request.setAttribute("kakaoresult", kakaoresult);
			request.setAttribute("login", result);
	}
}
