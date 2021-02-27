package com.cafe24.sharekim93.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.board.banner.BannerAction;
import com.cafe24.sharekim93.board.banner.BannerListAction;
import com.cafe24.sharekim93.board.img.ImgBoardAction;
import com.cafe24.sharekim93.board.img.ImgListAction;
@WebServlet("/index")
public class IndexController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ImgBoardAction comm = new ImgListAction(); comm.execute(req, resp);
		BannerAction bac = new BannerListAction(); bac.execute(req, resp);
		req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
