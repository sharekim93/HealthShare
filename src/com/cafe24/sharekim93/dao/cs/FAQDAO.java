package com.cafe24.sharekim93.dao.cs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.util.DB;

public class FAQDAO {
	private static FAQDAO bdao;
	
	private FAQDAO() {}
	
	public static FAQDAO getInstance() {
		if(bdao ==null) {bdao = new FAQDAO();}
		return bdao;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		DB db = new DB();
		conn = db.getConnection();
		return conn;
	}
	
	public ArrayList<Board> getList(){
		ArrayList<Board> list = new ArrayList<>();
		String sql = "SELECT * FROM health_faq ORDER BY fhit DESC";
		
		Connection conn = null; PreparedStatement stmt = null; ResultSet rset =null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			rset = stmt.executeQuery();
			while(rset.next()) {
				Board dto = new Board();
				dto.setBno(rset.getInt("fno"));
				dto.setBtitle(rset.getString("ftitle"));
				dto.setBhit(rset.getInt("fhit"));
				dto.setBcontent(rset.getString("fcontent"));
				list.add(dto);
			}
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		return list;
	}
		
	public int update_hit(Board dto) {
		int result=-1;
		String sql = "UPDATE health_faq SET fhit=fhit+1 WHERE fno=?";
		
		Connection conn = null; PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dto.getBno());
			result = stmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return result;
	}
}
