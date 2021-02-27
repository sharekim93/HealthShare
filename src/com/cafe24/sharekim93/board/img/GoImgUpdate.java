package com.cafe24.sharekim93.board.img;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.service.ImgBoardService;

public class GoImgUpdate implements ImgBoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bno_ = request.getParameter("bno");
		int bno=-1;
		if(bno_!=null&&bno_!="") {bno = Integer.parseInt(bno_);}
		
		Board dto = new Board();
		ImgBoardService service = new ImgBoardService();
		
		dto = service.getBoard(bno);
		request.setAttribute("b", dto);
	}

}
