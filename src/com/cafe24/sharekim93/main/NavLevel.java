package com.cafe24.sharekim93.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.dao.InfoBoardDAO;
import com.google.gson.Gson;
@WebServlet("/ajax/navlevel")
public class NavLevel extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		List<String> list = InfoBoardDAO.getInstance().getLevels();
		
		Gson gson = new Gson();
		String result = gson.toJson(list);
		out.print(result);
	}
}
