package com.cafe24.sharekim93.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardAction {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException; 
}
