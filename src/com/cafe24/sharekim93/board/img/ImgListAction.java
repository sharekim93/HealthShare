package com.cafe24.sharekim93.board.img;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.service.ImgBoardService;

public class ImgListAction implements ImgBoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Board> list = new ImgBoardService().getList();
		request.setAttribute("list", list);
	}

}
