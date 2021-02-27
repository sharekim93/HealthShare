package com.cafe24.sharekim93.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.service.BService;
import com.cafe24.sharekim93.service.Board2Service;
import com.cafe24.sharekim93.service.Board4Service;
import com.cafe24.sharekim93.service.BoardService;
import com.cafe24.sharekim93.service.InfoBoardService;

public class GoUpdate implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bno_ = request.getParameter("bno");
		int bno=-1;
		if(bno_!=null&&bno_!="") {bno = Integer.parseInt(bno_);}
		
		String category="1";
		String category_ = request.getParameter("c");
		if(category_!=null&&category_!="") {category=category_;}
		
		Board dto = new Board();
		BService service = new BoardService();
		if(category.equals("2")) {service = new Board2Service();}
		else if(category.equals("4")) {service = new Board4Service();}
		if(category.equals("5")) {service = new InfoBoardService();}
		dto.setBno(bno);
		dto = service.getBoard(dto);
		request.setAttribute("b", dto);
	}

}
