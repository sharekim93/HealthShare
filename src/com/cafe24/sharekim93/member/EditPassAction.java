package com.cafe24.sharekim93.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Member;
import com.cafe24.sharekim93.service.MypageService;

public class EditPassAction implements MemberAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid=String.valueOf(request.getSession().getAttribute("id"));
		String mpass = request.getParameter("mpass");
		String newpass = request.getParameter("newpass");
		int result = new MypageService().editPass(mid, mpass, newpass);
		request.setAttribute("editpass", result);
		Member member = new MypageService().getMember(mid);
		request.setAttribute("member",member);
	}
	
}
