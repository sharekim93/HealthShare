package com.cafe24.sharekim93.board.img;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.dao.ImageBoardDAO;
import com.cafe24.sharekim93.entity.Board;
import com.oreilly.servlet.MultipartRequest;

public class ImgWriteAction implements ImgBoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String realPath = request.getServletContext().getRealPath("/upload/");
		
		File path = new File(realPath);
		if(!path.exists()) {path.mkdirs();}
		
		System.out.println(realPath);
		MultipartRequest multi = new MultipartRequest(request, realPath,1024*1024*5,"UTF-8",null);
		PrintWriter out = response.getWriter();
		
		String bname = multi.getParameter("bname");
		String bpass = multi.getParameter("bpass");
		String btitle = multi.getParameter("btitle");
		String img = multi.getFilesystemName("file");
		String bcontent = multi.getParameter("bcontent");
		String bip = InetAddress.getLocalHost().getHostAddress();
				
		int result=-1;
		Board dto = new Board();
		dto.setBname(bname);
		dto.setBpass(bpass);
		dto.setBtitle(btitle);
		dto.setImg(img);
		dto.setBcontent(bcontent);
		dto.setBip(bip);
		result=ImageBoardDAO.getInstance().createBoard(dto);
		
		if(result>0) {out.println("<script>alert('글 작성 성공');location.href='list.pic';</script>");}
		else {out.println("<script>alert('글 작성 실패');history.go(-1);</script>");}
	}
}
