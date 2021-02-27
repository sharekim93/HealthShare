package com.cafe24.sharekim93.member;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Member;
import com.cafe24.sharekim93.service.JoinService;

public class JoinAction implements MemberAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String 	mid = request.getParameter("mid");	
		String	mname = request.getParameter("mname");
		String	mpass = request.getParameter("mpass");
		String	memail = request.getParameter("memail");
		String	mip = InetAddress.getLocalHost().getHostAddress();
		String	fourteen = request.getParameter("fourteen");
		String	minterest = Arrays.toString(request.getParameterValues("minterest"));
		String	val_minterest="";
		String	zonecode = request.getParameter("zonecode");
		String	address1 = request.getParameter("address1");
		String	address2 = request.getParameter("address2");
		String kakao_=request.getParameter("kakao");
		String kakao_id_ =  request.getParameter("kakao_id");
		String kakao="";
		String kakao_id=null;

		if(minterest==null||minterest=="null"){val_minterest="-";}
		else {val_minterest = minterest.toUpperCase().substring(1, minterest.length()-1);}
		if(kakao_!=null||kakao_!="null"){kakao="Y";}
		if(kakao=="Y"||kakao_id_!=null||kakao_id_!="null"){kakao_id=kakao_id_;}
		
		String msns = request.getParameter("mselect").toUpperCase();
		
		Member member = new Member(mid, mname, mpass, memail, mip, fourteen,val_minterest, msns,zonecode,address1,address2);
		member.setKakao_id(kakao_id);
		int result =new JoinService().insertMember(member);
		if(result>0) {
			member = new JoinService().getMember(mid);
			request.setAttribute("member", member);
		}
		request.setAttribute("join", result);
	}
	
}
