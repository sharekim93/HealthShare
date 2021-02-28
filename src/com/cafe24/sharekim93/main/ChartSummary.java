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
@WebServlet("/ajax/chart")
public class ChartSummary extends HttpServlet {

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
		
		//array의 col property에 해당하는 Array를 생성 후 데이터를 담습니다.
		JsonArray cols = new JsonArray();
		JsonObject idx1 = new JsonObject();
		idx1.addProperty("label", "근육부위");
		idx1.addProperty("id", "depth1");
		idx1.addProperty("type","string");
		
		JsonObject idx2 = new JsonObject();
		idx2.addProperty("label", "초급");
		idx2.addProperty("id", "low");
		idx2.addProperty("type","number");
		
		JsonObject idx3 = new JsonObject();
		idx3.addProperty("label", "중급");
		idx3.addProperty("id", "mid");
		idx3.addProperty("type","number");
		
		JsonObject idx4 = new JsonObject();
		idx4.addProperty("label", "상급");
		idx4.addProperty("id", "high");
		idx4.addProperty("type","number");
		
		cols.add(idx1);
		cols.add(idx2);
		cols.add(idx3);
		cols.add(idx4);
		
		data.add("cols",cols);
		
		//row property에 해당하는 Data를 담습니다.
		
		JsonArray rows = new JsonArray();

		for(int i=0;i<names.size();i++) {
			for(int j=0;j<categories.size();j++) {
				if(dao.getExerciseKind(categories.get(j), names.get(i))==0) {continue;}
				
				JsonObject cells = new JsonObject();
				JsonArray cell = new JsonArray();
						
				for(int k=0;k<4;k++) {
					JsonObject json = new JsonObject();
					if(k==0) { json.addProperty("v", names.get(i));}
					else if(k==j+1) {json.addProperty("v", dao.getExerciseKind(categories.get(j), names.get(i)));}
					else {json.addProperty("v", 0);}
					cell.add(json);
				}
				
				cells.add("c", cell);
				rows.add(cells);
			}
		}
		data.add("rows",rows);
		
		out.print(data);
	}
	
}
