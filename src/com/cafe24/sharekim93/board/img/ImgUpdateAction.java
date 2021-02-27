package com.cafe24.sharekim93.board.img;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.service.ImgBoardService;
import com.oreilly.servlet.MultipartRequest;

public class ImgUpdateAction implements ImgBoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletContext().getRealPath("/upload");
		
		PrintWriter out = response.getWriter();
		MultipartRequest multi = new MultipartRequest(request, path,1024*1024*5,"UTF-8");
		
		int 	bno  = Integer.parseInt(request.getParameter("bno"));
		String bname = multi.getParameter("bname");
		String bpass = multi.getParameter("bpass");
		String btitle = multi.getParameter("btitle");
		String bcontent = multi.getParameter("bcontent");
		String img 		= multi.getFilesystemName("img");
		String current	= multi.getParameter("current");
		
		Board dto = new Board();
		dto.setBno(bno);
		dto.setBname(bname);
		dto.setBpass(bpass);
		dto.setBtitle(btitle);
		dto.setBcontent(bcontent);
		dto.setImg(current);
		if(img!=null) {dto.setImg(img);}
		
		int result=-1;
		ImgBoardService service = new ImgBoardService();
		result = service.updateBoard(dto);
		
		if(result>0) {out.println("<script>alert('수정성공');location.href='list.pic;</script>");}
		else{out.println("<script>alert('수정실패');history.go(-1);</script>");}
	}
}
