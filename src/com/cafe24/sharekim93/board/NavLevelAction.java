package com.cafe24.sharekim93.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.service.InfoBoardService;

public class NavLevelAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InfoBoardService service = new InfoBoardService();
		request.setAttribute("levels", service.getLevels());
	}

}
