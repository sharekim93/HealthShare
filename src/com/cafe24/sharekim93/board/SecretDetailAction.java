package com.cafe24.sharekim93.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.service.BService;
import com.cafe24.sharekim93.service.Board4Service;

public class SecretDetailAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String bpass = request.getParameter("bpass");
		
		PrintWriter out = response.getWriter();
		Board dto = new Board();
		dto.setBno(bno); dto.setBpass(bpass);
		
		BService service = new Board4Service();
		int result = service.getSecretBoard(dto);
		if(result>0) {dto = service.getBoard(dto);request.getRequestDispatcher("detail.board?c=4&bno="+bno).forward(request, response);}
		else {out.print("<script>alert('비밀번호가 일치하지 않습니다');history.go(-1);</script>");}
	}

}
