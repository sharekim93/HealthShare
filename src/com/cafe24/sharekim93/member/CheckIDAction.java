package com.cafe24.sharekim93.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.dao.MemberDAO;

public class CheckIDAction implements MemberAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mid = request.getParameter("mid");
		PrintWriter out = response.getWriter();
		int result = MemberDAO.getInstance().checkID(mid);
		out.println(result);
	}

}
