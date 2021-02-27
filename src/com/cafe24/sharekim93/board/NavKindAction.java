package com.cafe24.sharekim93.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.service.InfoBoardService;

public class NavKindAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		InfoBoardService service = new InfoBoardService();
		request.setAttribute("depth", service.getDepth());
		
		out.print(service.getDepth());
	}

}
