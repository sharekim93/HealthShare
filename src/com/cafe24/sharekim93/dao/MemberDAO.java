package com.cafe24.sharekim93.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cafe24.sharekim93.entity.Member;
import com.cafe24.sharekim93.util.DB;

public class MemberDAO {
	private static MemberDAO mDAO;
	private MemberDAO() {}
	
	public static MemberDAO getInstance() {
		if(mDAO ==null) {mDAO = new MemberDAO();}
		return mDAO;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		DB db = new DB();
		conn = db.getConnection();		
		return conn;
	}
	
	public int insertMember(Member member) {
		int result=-1;
		PreparedStatement stmt=null; Connection conn = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(
					"insert into mvcmember2 (mid,mname,mpass,memail,mip,over_fourteen,minterest,msns,zonecode,address1,address2,kakao_id) values (?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, member.getMid());		stmt.setString(2, member.getMname()); 	stmt.setString(3, member.getMpass());
			stmt.setString(4, member.getMemail()); 	stmt.setString(5, member.getMip()); 	stmt.setString(6, member.getOver_fourteen());
			stmt.setString(7, member.getMinterest()); 	stmt.setString(8, member.getMsns()); stmt.setString(9, member.getZonecode());
			stmt.setString(10, member.getAddress1()); stmt.setString(11, member.getAddress2()); stmt.setString(12, member.getKakao_id());
			result = stmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return result;
	}
	
	public Member getMember(String mid) {
		Member member=null;
		PreparedStatement stmt=null; Connection conn = null; ResultSet rset=null;
		try{		
		conn = getConnection();
		stmt = conn.prepareStatement("SELECT * FROM mvcmember2 WHERE mid =?");
		stmt.setString(1, mid);
		rset = stmt.executeQuery();
			if(rset.next()){			
				member = new Member(rset.getString("mid"), rset.getString("mname"),rset.getString("memail"),rset.getString("address1"),
									rset.getString("mdate"), rset.getString("minterest"), rset.getString("msns"));
				member.setAddress2(rset.getString("address2"));	member.setZonecode(rset.getString("zonecode"));
				member.setKakao_id(rset.getString("kakao_id"));
			};
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return member;
	}
	
	public Member kakaoLogin(String kakaoid) {
		Member member=null;
		PreparedStatement stmt=null; Connection conn = null; ResultSet rset=null;
		try{		
		conn = getConnection();
		stmt = conn.prepareStatement("SELECT * FROM mvcmember2 WHERE kakao_id =?");
		stmt.setString(1, kakaoid);
		rset = stmt.executeQuery();
			if(rset.next()){			
				member = new Member(rset.getString("mid"), rset.getString("mname"),rset.getString("memail"),rset.getString("address1"),
									rset.getString("mdate"), rset.getString("minterest"), rset.getString("msns"));
				member.setAddress2(rset.getString("address2"));	member.setZonecode(rset.getString("zonecode"));
				member.setKakao_id(rset.getString("kakao_id"));
			};
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return member;
	}
	
	public Member getMember(String mid,String mpass) {
		Member member=null;
		PreparedStatement stmt=null; Connection conn = null; ResultSet rset=null;
		try{		
		conn = getConnection();
		stmt = conn.prepareStatement("SELECT * FROM mvcmember2 WHERE mid =? and mpass=?");
		stmt.setString(1, mid);
		stmt.setString(2, mpass);
		rset = stmt.executeQuery();
			if(rset.next()){			
				member = new Member(rset.getString("mid"), rset.getString("mname"),rset.getString("memail"),
									rset.getString("mdate"), rset.getString("minterest"), rset.getString("msns"));
			};
			System.out.println(member);
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return member;
	}
	
	public int editMember(Member input_member,String mid) {
		int result=-1;
		Member member = input_member;
		PreparedStatement stmt=null; Connection conn = null; 
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("update mvcmember2 set mname=?, memail=?, minterest=?, msns=?, zonecode=?,address1=?,address2=? where mid=?");
			stmt.setString(1, member.getMname());
			stmt.setString(2, member.getMemail());
			stmt.setString(3, member.getMinterest());
			stmt.setString(4, member.getMsns());
			stmt.setString(5, member.getZonecode());
			stmt.setString(6, member.getAddress1());
			stmt.setString(7, member.getAddress2());
			stmt.setString(8, mid);
			result = stmt.executeUpdate();
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(stmt !=null){try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn !=null){try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return result;
	}
	
	public int editMember(String mid,String mpass,String newpass) {
		int result=-1;
		PreparedStatement stmt=null; Connection conn = null; 
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("update mvcmember2 set mpass=? where mid=? and mpass=?");
			stmt.setString(1, newpass);
			stmt.setString(2, mid);
			stmt.setString(3, mpass);
			result = stmt.executeUpdate();
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(stmt !=null){try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn !=null){try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return result;
	}

	public int deleteMember(String mid, String mpass) {
		int result=-1;
		PreparedStatement stmt=null; Connection conn = null; ResultSet rset=null;
		try {
		conn = getConnection();
		stmt = conn.prepareStatement("select * from mvcmember2 where mid=? and mpass=?");
		stmt.setString(1, mid);
		stmt.setString(2,mpass);
		rset = stmt.executeQuery();
			if(rset.next()){
				stmt.close();
				stmt = conn.prepareStatement("delete from mvcmember2 where mid=?");
				stmt.setString(1, mid);
				result = stmt.executeUpdate();
			}
		}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset !=null){try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt !=null){try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn !=null){try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return result;
	}
	
	public int checkID(String mid) {
		int result=-1;
		PreparedStatement stmt=null; Connection conn = null; ResultSet rset=null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select count(*) total from mvcmember2 where mid=?");
			stmt.setString(1, mid);
			rset = stmt.executeQuery();
			rset.next();
			result=rset.getInt("total");
			}
		catch(Exception e){e.printStackTrace();}
		finally {
			if(rset!=null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt!=null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(conn!=null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return result;
	}
}
