package com.cafe24.sharekim93.member;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Member;
import com.cafe24.sharekim93.service.MypageService;

public class EditInfoAction implements MemberAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mid=String.valueOf(request.getSession().getAttribute("id"));
		String	mname = request.getParameter("mname");
		String	memail = request.getParameter("memail");
		String zonecode = request.getParameter("zonecode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String minterest = Arrays.toString(request.getParameterValues("minterest"));
		String val_minterest="";
		if(minterest==null||minterest=="null"){val_minterest="-";}
		else {val_minterest = minterest.toUpperCase().substring(1, minterest.length()-1);}
		String mselect = request.getParameter("mselect").toUpperCase();
		Member member = new Member(mid,mname,memail,val_minterest,mselect);
		member.setZonecode(zonecode); member.setAddress1(address1); member.setAddress2(address2);
		int result = new MypageService().editMember(member, mid);
		
		request.setAttribute("editinfo", result);
		member = new MypageService().getMember(mid);
		request.setAttribute("member",member);
	}

}
