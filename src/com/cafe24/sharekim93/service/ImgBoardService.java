package com.cafe24.sharekim93.service;

import java.util.List;

import com.cafe24.sharekim93.dao.ImageBoardDAO;
import com.cafe24.sharekim93.entity.Board;

public class ImgBoardService {

	public List<Board> getList() {
		return getList("btitle","",1);
	}
	public List<Board> getList(int page) {
		return getList("btitle","",page);
	}
	public List<Board> getList(String field, String query,int page) {		
		List<Board> list = ImageBoardDAO.getInstance().getList(field, query, page);
		return list;
	}
	
	public int getListCount() {		
		return getListCount("btitle","");
	}
	public int getListCount(String field, String query) {
		int count=ImageBoardDAO.getInstance().getListCount(field, query);
		return count;
	}
	public int createBoard(Board dto) {
		int result=-1;
		result = ImageBoardDAO.getInstance().createBoard(dto);
		return result;
	}
	
	public Board getBoard(int id) {
		Board board = ImageBoardDAO.getInstance().getBoard(id);
		return board;
	}
	public Board getNextBoard(int id) {
		Board board = ImageBoardDAO.getInstance().getNextBoard(id);
		return board;
	}
	public Board getPrevBoard(int id) {
		Board board = ImageBoardDAO.getInstance().getPrevBoard(id);
		return board;
	}
	public int updateBoard(Board dto) {
		int result=-1;
		result = ImageBoardDAO.getInstance().updateBoard(dto);
		return result;
	}
	
	public int deleteBoard(Board dto) {
		int result =-1;
		result = ImageBoardDAO.getInstance().deleteBoard(dto);
		return result;
	}
}
