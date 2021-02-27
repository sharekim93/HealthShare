package com.cafe24.sharekim93.board.banner;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.sharekim93.dao.BannerDAO;
import com.cafe24.sharekim93.entity.Banner;

public class BannerListAction implements BannerAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Banner> list = BannerDAO.getInstance().getList();
		request.setAttribute("bannerList", list);
	}
}
