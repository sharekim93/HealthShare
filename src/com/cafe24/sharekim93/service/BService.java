package com.cafe24.sharekim93.service;

import java.util.List;

import com.cafe24.sharekim93.entity.Board;

public interface BService {
	
	public List<Board> getList() ;
	public List<Board> getList(int page) ;
	public List<Board> getList(String field, String query,int page) ;
	
	public int getListCount() ;
	public int getListCount(String field, String query) ;
	public int createBoard(Board dto) ;
	
	public Board getBoard(Board dto) ;
	public int getSecretBoard(Board dto) ;

	public Board getNextBoard(int id) ;
	public Board getPrevBoard(int id) ;
	public int updateBoard(Board dto) ;
	public int reply(Board dto) ;
	
	public int deleteBoard(String bno, String bpass) ;
	public int getFilteredListCount(String levels, String depths,String field,String query);
}
