package com.cafe24.sharekim93.entity;

public class Board {
	private int rnum;
	private int bno;
	private String btitle;
	private String bname;
	private String bdate;
	private int bhit;
	private String bcontent;
	private String bip;
	private String bpass;
	private String img;
	private String bcategory;
	private int bhidden;
	private String level;
	private String depth1;
	private String depth2;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	public Board(int bno, String btitle, String bname, String bcontent) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bname = bname;
		this.bcontent = bcontent;
	}

	public Board(int bno, String btitle, String bname, String bdate, int bhit) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bname = bname;
		this.bdate = bdate;
		this.bhit = bhit;
	}
	
	public Board(int rnum, int bno, String btitle, String bname, String bdate, int bhit) {
		super();
		this.rnum = rnum;
		this.bno = bno;
		this.btitle = btitle;
		this.bname = bname;
		this.bdate = bdate;
		this.bhit = bhit;
	}
	
	public Board(int bno, int bhit, String btitle, String bname, String bcontent) {
		super();
		this.bno = bno;
		this.bhit = bhit;
		this.btitle = btitle;
		this.bname = bname;
		this.bcontent = bcontent;
	}

	public Board(int bno, String btitle, String bname, String bdate, int bhit, String bcontent) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bname = bname;
		this.bdate = bdate;
		this.bhit = bhit;
		this.bcontent = bcontent;
	}

	public Board(int bno, String btitle, String bname, String bdate, int bhit, String bcontent, String bip,
			String bpass) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bname = bname;
		this.bdate = bdate;
		this.bhit = bhit;
		this.bcontent = bcontent;
		this.bip = bip;
		this.bpass = bpass;
	}

	@Override
	public String toString() {
		return "Board [rnum=" + rnum + ", bno=" + bno + ", btitle=" + btitle + ", bname=" + bname + ", bdate=" + bdate
				+ ", bhit=" + bhit + ", bcontent=" + bcontent + ", bip=" + bip + ", bpass=" + bpass + ", img=" + img
				+ ", bcategory=" + bcategory + ", bhidden=" + bhidden + ", level=" + level + ", depth1=" + depth1
				+ ", depth2=" + depth2 + "]";
	}

	public int getBno() {
		return bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public String getBname() {
		return bname;
	}

	public String getBdate() {
		return bdate;
	}

	public int getBhit() {
		return bhit;
	}

	public String getBcontent() {
		return bcontent;
	}

	public String getBip() {
		return bip;
	}

	public String getBpass() {
		return bpass;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public void setBhit(int bhit) {
		this.bhit = bhit;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public void setBip(String bip) {
		this.bip = bip;
	}

	public void setBpass(String bpass) {
		this.bpass = bpass;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}

	public String getBcategory() {
		return bcategory;
	}

	public void setBcategory(String bcategory) {
		this.bcategory = bcategory;
	}

	public int getBhidden() {
		return bhidden;
	}

	public void setBhidden(int bhidden) {
		this.bhidden = bhidden;
	}

	public String getDepth1() {
		return depth1;
	}

	public String getDepth2() {
		return depth2;
	}

	public void setDepth1(String depth1) {
		this.depth1 = depth1;
	}

	public void setDepth2(String depth2) {
		this.depth2 = depth2;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
