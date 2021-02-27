package com.cafe24.sharekim93.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cafe24.sharekim93.entity.Banner;
import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.util.DB;

public class BannerDAO {
	private static BannerDAO dao;
	
	private BannerDAO() {}
	
	public static BannerDAO getInstance() {
		if(dao==null) {dao = new BannerDAO();}
		return dao;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		DB db = new DB();
		conn = db.getConnection();
		return conn;
	}
	
	public ArrayList<Banner> getList(){
		ArrayList<Banner> list = new ArrayList<>();
		String sql = "SELECT * FROM Health_banner";
		
		Connection conn = null; PreparedStatement stmt = null; ResultSet rset =null;
		
		try {
			conn = new DB().getConnection();
			stmt = conn.prepareStatement(sql);
			rset = stmt.executeQuery();
			while(rset.next()) {
				Banner banner = new Banner();
				banner.setBno(rset.getInt("imgno"));
				banner.setImg(rset.getString("img"));
				list.add(banner);
			}
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		return list;
	}
}
