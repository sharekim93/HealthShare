package com.cafe24.sharekim93.board.img;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.service.ImgBoardService;

public class ImgDeleteAction implements ImgBoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		int bno = Integer.parseInt(request.getParameter("bno"));
		String bpass = request.getParameter("bpass");
		int result=-1;
		
		ImgBoardService service = new ImgBoardService();
		Board dto = new Board();
		dto.setBno(bno);dto.setBpass(bpass);
		result = service.deleteBoard(dto);
		
		out.println(result);
	}
}
