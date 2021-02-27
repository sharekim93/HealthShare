package com.cafe24.sharekim93.entity;

public class Member {
	String mid;
	String mname;
	String mpass;
	String memail;
	String mip;
	String over_fourteen;
	String mdate;
	String minterest;
	String msns;
	String zonecode;
	String address1;
	String address2;
	String kakao_id;
	
	public Member(String mid, String mname, String memail, String mdate, String minterest, String msns) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.memail = memail;
		this.mdate = mdate;
		this.minterest = minterest;
		this.msns = msns;
	}
	
	public Member(String mid, String mname, String memail, String address1, String mdate,String minterest, String msns) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.memail = memail;
		this.address1 = address1;
		this.mdate = mdate;
		this.minterest = minterest;
		this.msns = msns;
	}
	
	public Member(String mid, String mname, String memail, String minterest, String msns) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.memail = memail;
		this.minterest = minterest;
		this.msns = msns;
	}
	
	public Member(String mid, String mname, String mpass, String memail, String mip, String over_fourteen,
			String minterest, String msns) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.mpass = mpass;
		this.memail = memail;
		this.mip = mip;
		this.over_fourteen = over_fourteen;
		this.minterest = minterest;
		this.msns = msns;
	}

	public Member(String mid, String mname, String mpass, String memail, String mip, String over_fourteen,
			String minterest, String msns, String zonecode, String address1, String address2) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.mpass = mpass;
		this.memail = memail;
		this.mip = mip;
		this.over_fourteen = over_fourteen;
		this.minterest = minterest;
		this.msns = msns;
		this.zonecode = zonecode;
		this.address1 = address1;
		this.address2 = address2;
	}

	@Override
	public String toString() {
		return "Member [mid=" + mid + ", mname=" + mname + ", memail=" + memail + ", mdate=" + mdate + ", minterest="
				+ minterest + ", msns=" + msns + "]";
	}
	public String getMid() {
		return mid;
	}
	public String getMname() {
		return mname;
	}
	public String getMemail() {
		return memail;
	}
	public String getMdate() {
		return mdate;
	}
	public String getMinterest() {
		return minterest;
	}
	public String getMsns() {
		return msns;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public void setMinterest(String minterest) {
		this.minterest = minterest;
	}
	public void setMsns(String msns) {
		this.msns = msns;
	}

	public String getMpass() {
		return mpass;
	}

	public String getMip() {
		return mip;
	}

	public String getOver_fourteen() {
		return over_fourteen;
	}

	public void setMpass(String mpass) {
		this.mpass = mpass;
	}

	public void setMip(String mip) {
		this.mip = mip;
	}

	public void setOver_fourteen(String over_fourteen) {
		this.over_fourteen = over_fourteen;
	}

	public String getZonecode() {
		return zonecode;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setZonecode(String zonecode) {
		this.zonecode = zonecode;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getKakao_id() {
		return kakao_id;
	}

	public void setKakao_id(String kakao_id) {
		this.kakao_id = kakao_id;
	}
	
	
}
