package com.cafe24.sharekim93.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.service.BService;
import com.cafe24.sharekim93.service.Board4Service;
import com.cafe24.sharekim93.service.BoardService;
import com.cafe24.sharekim93.service.InfoBoardService;

public class BReplyViewAction implements BoardAction{
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String c = request.getParameter("c");
		BService service = null;
		Board dto = new Board();
		dto.setBno(Integer.parseInt(request.getParameter("bno")));
		
		if(c.equals("1")) { service = new BoardService(); } 
		else if(c.equals("4")){ service = new Board4Service(); }
		if(c.equals("5")) {service = new InfoBoardService();}
		dto = service.getBoard(dto);
		
		dto.setBtitle("ㄴre:"+dto.getBtitle());
		String content = dto.getBcontent();
		String[] split = content.split("\r\n");
		String out="";
		for(String i:split) {
			i="//"+i;
			out+=i+"\r\n";
		}
		dto.setBcontent(out);
		request.setAttribute("b", dto);
	}
}
