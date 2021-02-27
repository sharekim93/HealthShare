package com.cafe24.sharekim93.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.service.BService;
import com.cafe24.sharekim93.service.Board4Service;
import com.cafe24.sharekim93.service.BoardService;
import com.cafe24.sharekim93.service.InfoBoardService;

public class BReplyAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bno_ = request.getParameter("bno");
		String bname = request.getParameter("bname");
		String bpass = request.getParameter("bpass");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String c = request.getParameter("c");
		PrintWriter out = response.getWriter();
		
		//bno가 get 요청으로 받아오기 때문에 null 방지
		int bno=-1;
		if(bno_!=null&&bno_!="") {bno = Integer.parseInt(bno_);}
		
		BService service = null;
		Board set = new Board();
		
		if(c.equals("1")) {service = new BoardService();}
		else if(c.equals("4")) {service = new Board4Service();}
		if(c.equals("5")) {service = new InfoBoardService();}
		
		set.setBno(bno);
		System.out.println("bno :"+bno);
		set.setBname(bname);
		System.out.println("bname : "+bname);
		set.setBpass(bpass);
		System.out.println("bpass : "+bpass);
		set.setBtitle(btitle);
		System.out.println("Btitle : "+btitle);
		set.setBcontent(bcontent);
		System.out.println("bcontent : "+bcontent);
		int result = service.reply(set);
		if(result>0) {out.print("<script>alert('답변성공');location.href='list.board?c="+c+"';</script>");}
		else {out.print("<script>alert('답변실패');history.go(-1);</script>");}
		
	}

}
