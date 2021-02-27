package com.cafe24.sharekim93.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.util.DB;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class InfoBoardDAO {
	private static InfoBoardDAO bdao;
	
	private InfoBoardDAO() {}
	
	public static InfoBoardDAO getInstance() {
		if(bdao ==null) {bdao = new InfoBoardDAO();}
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
			stmt = conn.prepareStatement("INSERT INTO mvcboard5	(BNAME, BPASS, BTITLE, BCONTENT, LEVEL, DEPTH1, DEPTH2)"
					+ "					   VALUES			(? , ? , ? , ? , ? , ? , ?)");
			stmt.setString(1, dto.getBname());
			stmt.setString(2, dto.getBpass());
			stmt.setString(3, dto.getBtitle());
			stmt.setString(4, dto.getBcontent());
			stmt.setString(5, dto.getBip());
			stmt.setString(6, dto.getLevel());
			stmt.setString(7, dto.getDepth1());
			stmt.setString(8, dto.getDepth2());
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
		
		String sql = "SELECT * FROM mvcboard5 WHERE BNO=?";
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
				stmt.close();
				stmt = conn.prepareStatement("UPDATE mvcboard5 SET BHIT=BHIT+1 WHERE BNO=?");
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
		String sql = "SELECT * FROM mvcboard5 m "
					+"WHERE BNO=(SELECT BNO FROM mvcboard5 WHERE BDATE>(SELECT BDATE FROM mvcboard5 WHERE BNO=?) LIMIT 1);";
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
				stmt = conn.prepareStatement("UPDATE mvcboard5 SET BHIT=BHIT+1 WHERE BNO=?");
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
		String sql = "SELECT * FROM mvcboard5 m "
					+"WHERE BNO=(SELECT BNO FROM mvcboard5 WHERE BDATE<(SELECT BDATE FROM mvcboard5 WHERE BNO=?) LIMIT 1);";
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
				stmt = conn.prepareStatement("UPDATE mvcboard5 SET BHIT=BHIT+1 WHERE BNO=?");
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
		stmt = conn.prepareStatement("delete from mvcboard5 where bno=? and bpass=?");
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
			stmt = conn.prepareStatement("UPDATE mvcboard5 SET BTITLE=?, BCONTENT=? WHERE BNO=? AND BPASS=?");
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
		String sql = "SELECT * FROM mvcboard5 WHERE "+field+" LIKE ? ORDER BY BDATE DESC LIMIT ?,10 ";
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
				board.setLevel(rset.getString("level"));
				board.setDepth1(rset.getString("depth1"));
				board.setDepth2(rset.getString("depth2"));
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
	
	public List<Board> getList(int page,String levels,String depths) {
		List<Board> list = new ArrayList<>();
		String sql = "SELECT * FROM ("+levels+") a ,("+depths+") b where a.BNO=b.BNO ORDER BY a.BDATE DESC LIMIT ?,10 ";
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, (page-1)*10);
			rset= stmt.executeQuery();
			while(rset.next()) {
				Board board = new Board(rset.getInt("bno"),
										rset.getString("btitle"),
										rset.getString("bname"),
										rset.getString("bdate").split(" ")[0],
										rset.getInt("bhit"));
				board.setLevel(rset.getString("level"));
				board.setDepth1(rset.getString("depth1"));
				board.setDepth2(rset.getString("depth2"));
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
		String sql = "SELECT count(*) cnt FROM"
				+" (SELECT * FROM mvcboard5 WHERE "+field+" LIKE ? ORDER BY BDATE) b";
		
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+query+"%");
			rset= stmt.executeQuery();
			if(rset.next()) {count=rset.getInt("cnt");}
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		return count;
	}
	
	public int getFilteredListCount(String levels, String depths) {
		int count=0;
		String sql = "SELECT count(*) cnt FROM ("+levels+") a ,("+depths+") b where a.BNO=b.BNO";
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			rset= stmt.executeQuery();
			if(rset.next()) {count=rset.getInt("cnt");}
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		return count;
	}
	
	public int getExerciseKind(String level, String depth) {
		int count=0;
		String sql = "SELECT count(*) cnt FROM (SELECT * FROM mvcboard5 WHERE LEVEL= ?) a ,"
				+ "(SELECT * FROM mvcboard5 WHERE DEPTH1 = ?) b where a.BNO=b.BNO";
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, level);
			stmt.setString(2, depth);
			rset= stmt.executeQuery();
			if(rset.next()) {count=rset.getInt("cnt");}
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		return count;
	}
	
	public ArrayList<String> getLevels(){
		ArrayList<String> list = new ArrayList<>();
		String sql = "SELECT distinct(LEVEL) levels FROM mvcboard5 ORDER BY levels desc";
		
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			rset= stmt.executeQuery();
			while(rset.next()) {list.add(rset.getString("levels"));}
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		return list;
	}
	
	public JsonArray getDepth(){
		JsonArray list = new JsonArray();
		String sql = "select distinct(depth1) primary_muscle ,depth2 from mvcboard5 order by primary_muscle";
		
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			rset= stmt.executeQuery();
			while(rset.next()) {
				JsonObject json = new JsonObject();
				json.addProperty(rset.getString("primary_muscle"),rset.getString("depth2"));
				list.add(json);}
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		return list;
	}
	
	public ArrayList<String> getDepth1(){
		ArrayList<String> list = new ArrayList<>();
		String sql = "select distinct(depth1) depth1 from mvcboard5 order by depth1";
		
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			rset= stmt.executeQuery();
			while(rset.next()) {
				list.add(rset.getString("depth1"));
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
}

