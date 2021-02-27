package com.cafe24.sharekim93.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.board.img.GoImgUpdate;
import com.cafe24.sharekim93.board.img.ImgBoardAction;
import com.cafe24.sharekim93.board.img.ImgDeleteAction;
import com.cafe24.sharekim93.board.img.ImgListAction;
import com.cafe24.sharekim93.board.img.ImgUpdateAction;
import com.cafe24.sharekim93.board.img.ImgWriteAction;

@WebServlet("*.pic")
public class ImgBoardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}
	
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String requestURL = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/"));
		System.out.println(requestURL);
		String path = "/WEB-INF/board/img/list.jsp";
		
		ImgBoardAction comm = null;

		if(requestURL.equals("/list.pic")) {comm=new ImgListAction();comm.execute(request, response);}
		else if(requestURL.equals("/gowrite.pic")) {path="/WEB-INF/board/img/write.jsp";}		
		else if(requestURL.equals("/write.pic")) {comm=new ImgWriteAction();comm.execute(request, response);return;}
		else if(requestURL.equals("/goupdate.pic")) {comm=new GoImgUpdate();comm.execute(request, response);path="/WEB-INF/board/img/update.jsp";}
		else if(requestURL.equals("/update.pic")) {comm=new ImgUpdateAction();comm.execute(request, response);return;}
		else if(requestURL.equals("/delete.pic")) {comm=new ImgDeleteAction();comm.execute(request, response);return;}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
