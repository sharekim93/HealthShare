package com.cafe24.sharekim93.service;

import com.cafe24.sharekim93.dao.MemberDAO;
import com.cafe24.sharekim93.entity.Member;

public class JoinService {
	
	public int insertMember(Member member) {
		int result = MemberDAO.getInstance().insertMember(member);
		return result;
	}
	
	public Member getMember(String mid) {
		Member member = MemberDAO.getInstance().getMember(mid);
		return member;
	}
	
	public int id_check(String mid) {
		int result=-1;
		Member member = MemberDAO.getInstance().getMember(mid);
		if(member!=null) {result=1;}
		return result;
	}
}
