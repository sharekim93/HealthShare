package com.cafe24.sharekim93.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.util.DB;

public class Board2DAO {
	private static Board2DAO bdao;
	
	private Board2DAO() {}
	
	public static Board2DAO getInstance() {
		if(bdao ==null) {bdao = new Board2DAO();}
		return bdao;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		DB db = new DB();
		conn = db.getConnection();
		return conn;
	}
	
	public int createBoard(Board dto) {
		Connection conn = null; PreparedStatement stmt =null; int result = -1;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("INSERT INTO mvcboard2	(BNAME, BPASS, BTITLE, BCONTENT, BIP)"
					+ "					   VALUES			(? , ? , ? , ? , ? )");
			stmt.setString(1, dto.getBname());
			stmt.setString(2, dto.getBpass());
			stmt.setString(3, dto.getBtitle());
			stmt.setString(4, dto.getBcontent());
			stmt.setString(5, dto.getBip());
			result = stmt.executeUpdate();
		}
		catch(Exception e) {e.printStackTrace();}
		finally {
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return result;
	}
	
	public Board getBoard(Board dto) {
		Board board = null;
		
		String sql = "SELECT * FROM mvcboard2 WHERE BNO=?";
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dto.getBno());
			rset= stmt.executeQuery();
			if(rset.next()) {
				board = new Board(rset.getInt("bno"),
								  rset.getInt("bhit"),
								  rset.getString("btitle"),
								  rset.getString("bname"),
								  rset.getString("bcontent").replace("\r\n", "<br>")
								  );
				board.setBdate(rset.getString("bdate").substring(0, rset.getString("bdate").lastIndexOf(":")));
				stmt.close();
				stmt = conn.prepareStatement("UPDATE mvcboard2 SET BHIT=BHIT+1 WHERE BNO=?");
				stmt.setInt(1, dto.getBno());
				stmt.executeUpdate();	
			}
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		return board;
	}
	public Board getNextBoard(int id) {
		Board board = null;
		String sql = "SELECT * FROM mvcboard2 m "
					+"WHERE BNO=(SELECT BNO FROM mvcboard2 WHERE BDATE>(SELECT BDATE FROM mvcboard2 WHERE BNO=?) LIMIT 1);";
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rset= stmt.executeQuery();
			if(rset.next()) {
				board = new Board(rset.getInt("bno"),
						  rset.getInt("bhit"),
						  rset.getString("btitle"),
						  rset.getString("bname"),
						  rset.getString("bcontent")
						  );
				stmt.close();
				stmt = conn.prepareStatement("UPDATE mvcboard2 SET BHIT=BHIT+1 WHERE BNO=?");
				stmt.setInt(1, id);
				stmt.executeUpdate();
			}
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return board;
	}
	public Board getPrevBoard(int id) {
		Board board = null;
		String sql = "SELECT * FROM mvcboard2 m "
					+"WHERE BNO=(SELECT BNO FROM mvcboard2 WHERE BDATE<(SELECT BDATE FROM mvcboard2 WHERE BNO=?) LIMIT 1);";
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rset= stmt.executeQuery();
			if(rset.next()) {
				board = new Board(rset.getInt("bno"),
						  rset.getInt("bhit"),
						  rset.getString("btitle"),
						  rset.getString("bname"),
						  rset.getString("bcontent")
						  );
				stmt.close();
				stmt = conn.prepareStatement("UPDATE mvcboard2 SET BHIT=BHIT+1 WHERE BNO=?");
				stmt.setInt(1, id);
				stmt.executeUpdate();	
			}
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return board;
	}
	
	public int deleteBoard(String bno, String bpass) {
		int result=-1;
		PreparedStatement stmt=null; Connection conn = null; ResultSet rset=null;
		try {
		conn = getConnection();
		stmt = conn.prepareStatement("delete from mvcboard2 where bno=? and bpass=?");
		stmt.setString(1, bno);
		stmt.setString(2, bpass);
		result = stmt.executeUpdate();
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset !=null){try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt !=null){try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn !=null){try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return result;	
	}
	
	public int updateBoard(Board dto) {
		int result=-1;
		Connection conn = null; PreparedStatement stmt =null; ResultSet rset=null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("UPDATE mvcboard2 SET BTITLE=?, BCONTENT=? WHERE BNO=? AND BPASS=?");
			stmt.setString(1, dto.getBtitle());
			stmt.setString(2, dto.getBcontent());
			stmt.setInt(3, dto.getBno());
			stmt.setString(4, dto.getBpass());
			result = stmt.executeUpdate();			
		}
		catch(Exception e) {e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return result;
	}
	
	public List<Board> getList(String field, String query,int page) {
		List<Board> list = new ArrayList<>();
		String sql = "SELECT * FROM mvcboard2 WHERE "+field+" LIKE ? ORDER BY BDATE DESC LIMIT ?,10 ";
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+query+"%");
			stmt.setInt(2, (page-1)*10);
			rset= stmt.executeQuery();
			while(rset.next()) {
				Board board = new Board(rset.getInt("bno"),
										rset.getString("btitle"),
										rset.getString("bname"),
										rset.getString("bdate").split(" ")[0],
										rset.getInt("bhit"));
				list.add(board);
			}
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return list;
	}
	
	public int getListCount(String field, String query) {
		int count=0;
		String sql = "SELECT count(*) count FROM"
				+" (SELECT * FROM mvcboard2 WHERE "+field+" LIKE ? ORDER BY BDATE) b";
		
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+query+"%");
			rset= stmt.executeQuery();
			if(rset.next()) {count=rset.getInt("count");}
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		return count;
	}
}
