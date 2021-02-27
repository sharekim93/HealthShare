package com.cafe24.sharekim93.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.dao.InfoBoardDAO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
@WebServlet("/ajax/grid")
public class GridSummary extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		InfoBoardDAO dao = InfoBoardDAO.getInstance();
		
		//난이도 목록을 가져옵니다
		ArrayList<String> categories = dao.getLevels();
		//부위 목록을 가져옵니다
		ArrayList<String> names = dao.getDepth1();

		//난이도에 따른 근육부위별 운동수를 JsonArray에 담습니다.
		JsonArray array = new JsonArray();
		for(String cat:categories) {
			for(int i=0;i<names.size();i++) {
				if(dao.getExerciseKind(cat, names.get(i))==0) {continue;}
				JsonObject json = new JsonObject();
				json.addProperty("level", cat);
				json.addProperty("depth1", names.get(i));
				json.addProperty("count", dao.getExerciseKind(cat, names.get(i)));
				array.add(json);
			}
		}
		out.print(array);
	}
	
}
