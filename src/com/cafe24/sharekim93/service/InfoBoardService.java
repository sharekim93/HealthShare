package com.cafe24.sharekim93.service;

import java.util.ArrayList;
import java.util.List;

import com.cafe24.sharekim93.dao.InfoBoardDAO;
import com.cafe24.sharekim93.entity.Board;
import com.google.gson.JsonArray;

public class InfoBoardService implements BService{

	public List<Board> getList() {
		return getList("btitle","",1);
	}
	public List<Board> getList(int page) {
		return getList("btitle","",page);
	}
	
	public List<Board> getList(String field, String query, int page) {
		List<Board> list = InfoBoardDAO.getInstance().getList(field, query, page);
		return list;
	}
	
	public int getListCount() {		
		return getListCount("btitle","");
	}
	public int getListCount(String field, String query) {
		int count=InfoBoardDAO.getInstance().getListCount(field, query);
		return count;
	}
	public int createBoard(Board dto) {
		int result=-1;
		result = InfoBoardDAO.getInstance().createBoard(dto);
		return result;
	}
	
	public Board getBoard(Board dto) {
		Board board = InfoBoardDAO.getInstance().getBoard(dto);
		return board;
	}
	
	public Board getNextBoard(int id) {
		Board board = InfoBoardDAO.getInstance().getNextBoard(id);
		return board;
	}
	public Board getPrevBoard(int id) {
		Board board = InfoBoardDAO.getInstance().getPrevBoard(id);
		return board;
	}
	public int updateBoard(Board dto) {
		int result=-1;
		result = InfoBoardDAO.getInstance().updateBoard(dto);
		return result;
	}
	
	public int deleteBoard(String bno, String bpass) {
		int result =-1;
		result = InfoBoardDAO.getInstance().deleteBoard(bno, bpass);
		return result;
	}
	
	public ArrayList<String> getLevels(){
		ArrayList<String> list = null;
		list = InfoBoardDAO.getInstance().getLevels();
		return list;
	}
	
	public JsonArray getDepth(){
		JsonArray list =null;
		list = InfoBoardDAO.getInstance().getDepth();
		return list;
	}

	@Override
	public int getSecretBoard(Board dto) {
		return 0;
	}
	@Override
	public int reply(Board dto) {
		return 0;
	}
	@Override
	public int getFilteredListCount(String levels, String depths) {
		int count=InfoBoardDAO.getInstance().getFilteredListCount(levels, depths);
		return count;
	}

}
