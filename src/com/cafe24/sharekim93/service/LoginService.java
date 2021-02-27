package com.cafe24.sharekim93.service;

import com.cafe24.sharekim93.dao.MemberDAO;
import com.cafe24.sharekim93.entity.Member;

public class LoginService {
	
	public int Login(String mid, String mpass) {
		int result=-1;
		Member member = MemberDAO.getInstance().getMember(mid, mpass);
		if(member!=null) {result=1;}
		return result;
	}
	
	public String Login(String kakaoid) {
		String result="";
		Member member = MemberDAO.getInstance().kakaoLogin(kakaoid);
		if(member!=null) {result=member.getMid();}
		return result;
		
	}
}
