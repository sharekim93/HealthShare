package com.cafe24.sharekim93.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.board.BReplyAction;
import com.cafe24.sharekim93.board.BReplyViewAction;
import com.cafe24.sharekim93.board.BoardAction;
import com.cafe24.sharekim93.board.DeleteAction;
import com.cafe24.sharekim93.board.DetailAction;
import com.cafe24.sharekim93.board.GoUpdate;
import com.cafe24.sharekim93.board.ListAction;
import com.cafe24.sharekim93.board.NavKindAction;
import com.cafe24.sharekim93.board.NavLevelAction;
import com.cafe24.sharekim93.board.SecretDetailAction;
import com.cafe24.sharekim93.board.UpdateAction;
import com.cafe24.sharekim93.board.WriteAction;

@WebServlet("*.board")
public class BoardController extends HttpServlet {

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
		String path = "/WEB-INF/board/list.jsp";
		
		BoardAction comm = null;
		
		if(requestURL.equals("/list.board")) {}
		if(requestURL.equals("/getlist.board")) {comm=new ListAction(); comm.execute(request, response);return;}
		else if(requestURL.equals("/gowrite.board")) {path="/WEB-INF/board/write.jsp";}
		else if(requestURL.equals("/write.board")) {comm=new WriteAction(); comm.execute(request, response);return;}
		else if(requestURL.equals("/detail.board")) {comm=new DetailAction(); comm.execute(request, response);
													 path="/WEB-INF/board/detail.jsp";}
		else if(requestURL.equals("/goupdate.board")) {comm=new GoUpdate(); comm.execute(request, response);
													   path="/WEB-INF/board/update.jsp";}
		else if(requestURL.equals("/update.board")) {comm=new UpdateAction(); comm.execute(request, response);return;}
		else if(requestURL.equals("/delete.board")) {comm=new DeleteAction(); comm.execute(request, response);return;}
		else if(requestURL.equals("/notice.board")) {path="list.board?c=2";}
		else if(requestURL.equals("/free.board")) {path="list.board?c=1";}
		else if(requestURL.equals("/secret.board")) {path="list.board?c=4";}
		else if(requestURL.equals("/reply_view.board")) {comm=new BReplyViewAction(); comm.execute(request, response);path="/WEB-INF/board/reply.jsp";}
		else if(requestURL.equals("/reply.board")) {comm=new BReplyAction(); comm.execute(request, response);return;}
		else if(requestURL.equals("/secret_pass.board")) {path="/WEB-INF/board/secret_pass.jsp";}
		else if(requestURL.equals("/secret_detail.board")) {comm=new SecretDetailAction();comm.execute(request, response);return;}
		else if(requestURL.equals("/info.board")) {comm = new NavLevelAction(); comm.execute(request, response); path="/WEB-INF/board/info_list.jsp?c=5";}	
		else if(requestURL.equals("/getNavMuscle.board")) {comm = new NavKindAction(); comm.execute(request, response);return;}		
		
		request.getRequestDispatcher(path).forward(request, response);
	}
}
