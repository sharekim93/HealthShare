package com.cafe24.sharekim93.entity;

import java.util.Arrays;

public class Summary {
	private String name;
	private int[] data;
	
	public Summary() {
	
	}

	@Override
	public String toString() {
		return "{name=\"" + name + "\", data=" + Arrays.toString(data) + "}";
	}

	public String getName() {
		return name;
	}

	public int[] getData() {
		return data;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setData(int[] data) {
		this.data = data;
	}
	
	
}
