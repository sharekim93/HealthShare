package com.cafe24.sharekim93.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.service.BService;
import com.cafe24.sharekim93.service.Board2Service;
import com.cafe24.sharekim93.service.Board4Service;
import com.cafe24.sharekim93.service.BoardService;
import com.cafe24.sharekim93.service.InfoBoardService;

public class WriteAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String bname = request.getParameter("bname");
		String bpass = request.getParameter("bpass");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bip = InetAddress.getLocalHost().getHostAddress();
		String bhidden_ = request.getParameter("bhidden");
		Object mid_ = request.getSession().getAttribute("id");
		String category="1";
		String category_ = request.getParameter("c");
		int bhidden=0;
		String mid = null;
		
		if(category_!=null&&category_!="") {category=category_;}
		if(bhidden_!=null&&bhidden_!="") {bhidden=Integer.parseInt(bhidden_);}
		if(mid_!=null) {mid = String.valueOf(mid_);}
		int result=-1;
		BService service = new BoardService();
		if(category.equals("2")) {service = new Board2Service();}
		else if(category.equals("4")) {service = new Board4Service();}
		if(category.equals("5")) {service = new InfoBoardService();}
		
		Board dto = new Board();
		dto.setBname(bname);
		dto.setBpass(bpass);
		dto.setBtitle(btitle);
		dto.setBcontent(bcontent);
		dto.setBip(bip);
		dto.setBhidden(bhidden);
		dto.setMid(mid);
		result=service.createBoard(dto);
		
		if(result>0) {out.println("<script>alert('글 작성 성공');location.href='list.board?c="+category+"';</script>");}
		else {out.println("<script>alert('글 작성 실패');location.href='write.board?c="+category+"';</script>");}
	}
	
	
}
