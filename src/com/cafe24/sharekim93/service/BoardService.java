package com.cafe24.sharekim93.service;

import java.util.List;

import com.cafe24.sharekim93.dao.BoardDAO;
import com.cafe24.sharekim93.entity.Board;

public class BoardService implements BService{

	public List<Board> getList() {
		return getList("btitle","",1);
	}
	public List<Board> getList(int page) {
		return getList("btitle","",page);
	}
	public List<Board> getList(String field, String query,int page) {		
		List<Board> list = BoardDAO.getInstance().getList(field, query, page);
		return list;
	}
	
	public int getListCount() {		
		return getListCount("btitle","");
	}
	public int getListCount(String field, String query) {
		int count=BoardDAO.getInstance().getListCount(field, query);
		return count;
	}
	public int createBoard(Board dto) {
		int result=-1;
		result = BoardDAO.getInstance().createBoard(dto);
		return result;
	}
	
	public Board getBoard(Board dto) {
		Board board = BoardDAO.getInstance().getBoard(dto);
		return board;
	}
	
	public int getSecretBoard(Board dto) {
		int result=-1;
		
		return result;
	}

	public Board getNextBoard(int id) {
		Board board = BoardDAO.getInstance().getNextBoard(id);
		return board;
	}
	public Board getPrevBoard(int id) {
		Board board = BoardDAO.getInstance().getPrevBoard(id);
		return board;
	}
	public int updateBoard(Board dto) {
		int result=-1;
		result = BoardDAO.getInstance().updateBoard(dto);
		return result;
	}
	public int reply(Board dto) {
		int result=-1;
		BoardDAO.getInstance().update_re1(dto);
		result = BoardDAO.getInstance().update_re2(dto);
		return result;
	}
	
	public int deleteBoard(String bno, String bpass) {
		int result =-1;
		result = BoardDAO.getInstance().deleteBoard(bno, bpass);
		return result;
	}
	@Override
	public int getFilteredListCount(String levels, String depths,String field,String query) {
		return 0;
	}
}
