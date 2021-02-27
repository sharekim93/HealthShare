package com.cafe24.sharekim93.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.service.BService;
import com.cafe24.sharekim93.service.Board2Service;
import com.cafe24.sharekim93.service.Board4Service;
import com.cafe24.sharekim93.service.BoardService;
import com.cafe24.sharekim93.service.InfoBoardService;

public class UpdateAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String bno_ = request.getParameter("bno");
		int bno=-1;
		if(bno_!=null&&bno_!="") {bno = Integer.parseInt(bno_);}
		String btitle = request.getParameter("btitle");
		String bcontents = request.getParameter("bcontents");
		String bpass = request.getParameter("bpass");
		
		String category="1";
		String category_ = request.getParameter("c");
		if(category_!=null&&category_!="") {category=category_;}
		
		int bhidden=0;
		String bhidden_ = request.getParameter("bhidden");
		if(bhidden_!=null&&bhidden_!="") {bhidden=Integer.parseInt(bhidden_);}
		int result = -1;
		BService service = new BoardService();
		if(category.equals("2")) {service = new Board2Service();}
		else if(category.equals("4")) {service = new Board4Service();}
		if(category.equals("5")) {service = new InfoBoardService();}
		
		Board dto = new Board();
		dto.setBno(bno); 
		dto.setBtitle(btitle); 
		dto.setBcontent(bcontents); dto.setBpass(bpass); dto.setBhidden(bhidden);
		result = service.updateBoard(dto);
		
		if(result>0) {out.println("<script>alert('수정에 성공했습니다.');location.href='detail.board?bno="+bno+"&c="+category+"';</script>");}
		else {out.println("<script>alert('수정에 실패했습니다.');location.href='goupdate.board?bno="+bno+"&c="+category+"';</script>");}
	}
}
