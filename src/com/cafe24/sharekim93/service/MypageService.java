package com.cafe24.sharekim93.service;

import com.cafe24.sharekim93.dao.MemberDAO;
import com.cafe24.sharekim93.entity.Member;

public class MypageService {
	
	public Member getMember(String mid) {
		Member member = MemberDAO.getInstance().getMember(mid);
		return member;
	}
	
	public int editPass(String mid,String mpass,String newpass) {
		int result=-1;
		result = MemberDAO.getInstance().editMember(mid, mpass, newpass);
		return result;
	}
	
	public int editMember(Member input_member,String mid) {
		int result = MemberDAO.getInstance().editMember(input_member, mid);
		return result;
	}
	
	public int deleteMember(String mid, String mpass) {
		int result = MemberDAO.getInstance().deleteMember(mid, mpass);
		return result;
	}
}
