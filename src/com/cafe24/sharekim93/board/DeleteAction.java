package com.cafe24.sharekim93.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.service.BService;
import com.cafe24.sharekim93.service.Board2Service;
import com.cafe24.sharekim93.service.Board4Service;
import com.cafe24.sharekim93.service.BoardService;
import com.cafe24.sharekim93.service.InfoBoardService;

public class DeleteAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String bno = request.getParameter("bno");
		String bpass = request.getParameter("bpass");
		int result=-1;
		String category="1";
		String category_ = request.getParameter("c");
		if(category_!=null&&category_!="") {category=category_;}
		
		BService service = new BoardService();
		if(category.equals("2")) {service = new Board2Service();}
		else if(category.equals("4")) {service = new Board4Service();}
		if(category.equals("5")) {service = new InfoBoardService();}
		result = service.deleteBoard(bno, bpass);
		
		if(result >0){out.println("<script>alert('삭제 되었습니다');location.href='list.board?c="+category+"';</script>");}
		else {out.println("<script>alert('비밀번호를 확인하세요');history.go(-1);</script>");}
	}
}
