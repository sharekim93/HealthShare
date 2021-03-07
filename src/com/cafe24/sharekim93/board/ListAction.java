package com.cafe24.sharekim93.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.dao.InfoBoardDAO;
import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.entity.JsonList;
import com.cafe24.sharekim93.service.BService;
import com.cafe24.sharekim93.service.Board2Service;
import com.cafe24.sharekim93.service.Board4Service;
import com.cafe24.sharekim93.service.BoardService;
import com.cafe24.sharekim93.service.InfoBoardService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class ListAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().create(); 
		String levels="SELECT * FROM mvcboard5";
		String depths="SELECT * FROM mvcboard5";
		String field_ 		= request.getParameter("f");
		String query_		= request.getParameter("q");
		String page_		= request.getParameter("p");
		String category_	= request.getParameter("c");
		String source_		= request.getParameter("json");
		String level_		= request.getParameter("level");
		String depth1_		= request.getParameter("muscle");
		
		String field		= "btitle";
		String query		= "";
		int	   page			= 1;
		String category		= "1";
		String source		= "";
		int count=1;
		
		if(field_!=null&&field_!="") {field=field_;}
		if(query_!=null&&query_!="") {query=query_;}
		if(page_!=null&&page_!="") {page=Integer.parseInt(page_);}
		if(category_!=null&&category_!="") {category=category_;}
		if(level_!=null&&level_!="") {levels=levels+" WHERE LEVEL = "+level_;}
		if(depth1_!=null&&depth1_!="") {depths=depths+" WHERE DEPTH1 = "+depth1_;}
		if(source_!=null&&source_!="") { 
			source=source_;
			JsonParser parser = new JsonParser();
			JsonArray array =  parser.parse(source).getAsJsonArray();
			  boolean level_first=true;
			  boolean depths_first=true;

			  for (JsonElement elem:array) {
				  JsonObject json = elem.getAsJsonObject(); 
				  if(json.get("level")!=null) {
					  try{ 
						  if(level_first) {level_first=false;levels+=" WHERE ";}
						  else {levels+=" or ";}
						  levels+="level ="+json.get("level").toString().replace("\"", "'"); 
					  }catch(Exception e) {}
				  }
				  else {
					  try {
						  if(depths_first) {depths_first=false;depths+=" WHERE ";}
						  else {depths+=" or ";}
						  depths+="depth1 = "+json.get("depth1").toString().replace("\"", "'"); 
					  }catch(Exception e) {} 
				  }
			  }
		}
		List<Board> list = new ArrayList<>();
		PrintWriter out = response.getWriter();
		JsonList jsonlist = new JsonList();
		
		BService service = new BoardService();
		if(category.equals("2")) {service = new Board2Service();}
		if(category.equals("4")) {service = new Board4Service();}
		if(category.equals("5")) {service = new InfoBoardService();}
		
		if((source_!=null&&source_!="")||(level_!=null&&level_!="")||(depth1_!=null&&depth1_!="")) {
			count  = service.getFilteredListCount(levels, depths,field,query);
			list = InfoBoardDAO.getInstance().getList(page,levels,depths,field,query);
		}
		else {
			count  = service.getListCount(field, query);
			list = service.getList(field,query,page);
			}
		
		jsonlist.setCount(count);
		//pageLimit : 한 페이지당 보여주는 레코드 수
		jsonlist.setPageLimit(10);
		//pageAll : 전체 페이지 수
		jsonlist.setPageAll((int)Math.ceil(count/10f));
		//bottomList : 10
		int bottomList=10;
		jsonlist.setBottomList(bottomList);
		//page : 현재 페이지 번호
		jsonlist.setPage(page);
		//startNum : 페이지 시작 번호
		jsonlist.setStartNum(page-(page-1)%10);
		//lastNum : 페이지 마지막 번호
		jsonlist.setLastNum((int)Math.ceil(count/10f));
		
		jsonlist.setList(list);
		
		String jsonResult = gson.toJson(jsonlist);
		out.println(jsonResult);
		
	}

}
