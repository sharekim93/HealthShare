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
@WebServlet("/ajax/treemap")
public class TreeMapSummary extends HttpServlet {

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

		//Goole Chart 에 담을 Data를 JsonArray로 생성합니다.
		JsonObject data = new JsonObject();
		JsonArray series = new JsonArray();
		
		for(int i=0;i<categories.size();i++) {
			JsonObject label = new JsonObject();
			label.addProperty("label", categories.get(i));
			
			JsonArray children = new JsonArray();
			for(int j=0;j<names.size();j++) {
				JsonObject child = new JsonObject();
				child.addProperty("label", names.get(j));
				child.addProperty("data", dao.getExerciseKind(categories.get(i), names.get(j)));
				children.add(child);
			}
			label.add("children", children);
			series.add(label);
		}
		
		data.add("series", series);
		
		out.print(data);

	}
	
}
