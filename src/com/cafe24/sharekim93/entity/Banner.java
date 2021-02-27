package com.cafe24.sharekim93.entity;

public class Banner {
	private int bno;
	private String img;
	
	public Banner() {  }

	@Override
	public String toString() {
		return "Banner [bno=" + bno + ", img=" + img + "]";
	}

	public int getBno() {
		return bno;
	}

	public String getImg() {
		return img;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
