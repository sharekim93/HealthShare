package com.cafe24.sharekim93.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.sharekim93.entity.Board;
import com.cafe24.sharekim93.util.DB;

public class Board4DAO {
	private static Board4DAO bdao;
	
	private Board4DAO() {}
	
	public static Board4DAO getInstance() {
		if(bdao ==null) {bdao = new Board4DAO();}
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
		String sql = "SELECT * FROM mvcboard4 ORDER BY BSTEP DESC, BINDENT ASC";
		
		Connection conn = null; PreparedStatement stmt = null; ResultSet rset =null;
		
		try {
			conn = new DB().getConnection();
			stmt = conn.prepareStatement(sql);
			rset = stmt.executeQuery();
			while(rset.next()) {
				String title = "";
				if(rset.getInt("bhidden")==1) {title = "비밀글입니다";} else{title =rset.getString("btitle");}
				Board board = new Board(rset.getInt("bno"),
										title,
										rset.getString("bname"),
										rset.getString("bdate").split(" ")[0],
										rset.getInt("bhit"));
				list.add(board);
			}
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		return list;
	}
	
	public List<Board> getList(String field, String query,int page) {
		List<Board> list = new ArrayList<>();
		String sql = "SELECT * FROM mvcboard4 WHERE "+field+" LIKE ? ORDER BY BSTEP DESC, BINDENT ASC LIMIT ?,10 ";
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+query+"%");
			stmt.setInt(2, (page-1)*10);
			rset= stmt.executeQuery();
			while(rset.next()) {
				String title = "";
				if(rset.getInt("bhidden")==1) {title = "비밀글입니다";} else{title =rset.getString("btitle");}
				Board board = new Board(rset.getInt("bno"),
										title,
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
				+" (SELECT * FROM mvcboard4 WHERE "+field+" LIKE ? ORDER BY BDATE) b";
		
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
	
	public int createBoard(Board dto) {
		Connection conn = null; PreparedStatement stmt =null; int result = -1;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("INSERT INTO mvcboard4	(BNAME, BPASS, BTITLE, BCONTENT, BHIT, BIP, BCATEGORY, BGROUP, BSTEP, BINDENT)"
					+ "					   VALUES			(? , ? , ? , ? , 0 , ? , 'BCATEGORY' ,"
					+ "					 					IFNULL((SELECT MAX(BGROUP) FROM mvcboard4 a),0)+1,"
					+ "					 					(IFNULL((SELECT MAX(BGROUP) FROM mvcboard4 b),0)+1)*1000,0)");
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
		
		String sql = "SELECT * FROM mvcboard4 WHERE BNO=?";
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
				board.setBhidden(rset.getInt("bhidden"));
				board.setBdate(rset.getString("bdate").substring(0, rset.getString("bdate").lastIndexOf(":")));
				stmt.close();
				stmt = conn.prepareStatement("UPDATE mvcboard4 SET BHIT=BHIT+1 WHERE BNO=?");
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
	
	public Board getSecretBoard(Board dto) {
		Board board = null;
		
		String sql = "SELECT * FROM mvcboard4 WHERE BNO=? and BPASS=?";
		Connection conn=null; PreparedStatement stmt=null; ResultSet rset=null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dto.getBno());
			stmt.setString(2, dto.getBpass());
			rset= stmt.executeQuery();
			if(rset.next()) {
				board = new Board(rset.getInt("bno"),
								  rset.getInt("bhit"),
								  rset.getString("btitle"),
								  rset.getString("bname"),
								  rset.getString("bcontent").replace("\r\n", "<br>")
								  );
				board.setBhidden(rset.getInt("bhidden"));
				stmt.close();
				stmt = conn.prepareStatement("UPDATE mvcboard4 SET BHIT=BHIT+1 WHERE BNO=?");
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
		String sql = "SELECT * FROM mvcboard4 m "
					+"WHERE BNO=(SELECT BNO FROM mvcboard4 WHERE BDATE>(SELECT BDATE FROM mvcboard4 WHERE BNO=?) LIMIT 1);";
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
				stmt = conn.prepareStatement("UPDATE mvcboard4 SET BHIT=BHIT+1 WHERE BNO=?");
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
		String sql = "SELECT * FROM mvcboard4 m "
					+"WHERE BNO=(SELECT BNO FROM mvcboard4 WHERE BDATE<(SELECT BDATE FROM mvcboard4 WHERE BNO=?) LIMIT 1);";
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
				stmt = conn.prepareStatement("UPDATE mvcboard4 SET BHIT=BHIT+1 WHERE BNO=?");
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
	public int update_hit(Board dto) {
		int result=-1;
		String sql = "UPDATE mvcboard4 SET BHIT=BHIT+1 WHERE BNO=?";
		
		Connection conn = null; PreparedStatement stmt = null;
		
		try {
			conn = new DB().getConnection();
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
	
	public int updateBoard(Board dto) {
		int result=-1;
		Connection conn = null; PreparedStatement stmt =null; ResultSet rset=null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("UPDATE mvcboard4 SET BTITLE=?, BCONTENT=?, BHIDDEN=? WHERE BNO=? AND BPASS=?");
			stmt.setString(1, dto.getBtitle());
			stmt.setString(2, dto.getBcontent());
			stmt.setInt(3, dto.getBhidden());
			stmt.setInt(4, dto.getBno());
			stmt.setString(5, dto.getBpass());
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
	
	public int update_re1(Board dto) {
		int result=-1;
		String sql = "update mvcboard4 set bstep = bstep-1 "
				+ " where bstep>(SELECT prev FROM (SELECT CEIL(BSTEP/1000)*1000-1000 prev FROM mvcboard4 WHERE BNO=?) a )"
				+ " and bstep<(SELECT BSTEP FROM (SELECT BSTEP FROM mvcboard4 WHERE BNO=?) b)";
		
		Connection conn = null; PreparedStatement stmt = null;
		
		try {
			conn = new DB().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dto.getBno());
			stmt.setInt(2, dto.getBno());
			result = stmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return result;
	}
	
	public int update_re2(Board dto) {
		int result=-1;String bip="미반영";
		String sql = "insert into mvcboard4 (bname,bpass,btitle,bcontent,bip,bgroup,bstep,bindent)"
				+ "		values (?,?,?,?,?,(SELECT CEIL(BSTEP/1000) FROM mvcboard4 a WHERE BNO=?),"
				+ "		(SELECT BSTEP FROM (SELECT BSTEP FROM mvcboard4 WHERE BNO=?) b)-1 ,(SELECT BINDENT FROM (SELECT BINDENT FROM mvcboard4 WHERE BNO=?) c)+1)";
		try {bip = InetAddress.getLocalHost().getHostAddress();} catch (UnknownHostException e1) {e1.printStackTrace();}
		Connection conn = null; PreparedStatement stmt = null;
		
		try {
			conn = new DB().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getBname());
			stmt.setString(2, dto.getBpass());
			stmt.setString(3, dto.getBtitle());
			stmt.setString(4, dto.getBcontent());
			stmt.setString(5, bip);
			stmt.setInt(6, dto.getBno());
			stmt.setInt(7, dto.getBno());
			stmt.setInt(8, dto.getBno());
			result = stmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return result;
	}
	
	public int deleteBoard(String bno, String bpass) {
		int result=-1;
		PreparedStatement stmt=null; Connection conn = null; ResultSet rset=null;
		try {
		conn = getConnection();
		stmt = conn.prepareStatement("delete from mvcboard4 where bno=? and bpass=?");
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
}
