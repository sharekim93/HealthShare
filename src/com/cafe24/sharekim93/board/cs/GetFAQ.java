package com.cafe24.sharekim93.board.cs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.dao.cs.FAQDAO;
import com.cafe24.sharekim93.entity.Board;
import com.google.gson.Gson;

public class GetFAQ implements CSBoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		List<Board> list = FAQDAO.getInstance().getList();
		
		Gson gson = new Gson();
		String result = gson.toJson(list);
		
		out.print(result);
	}

}
