package com.cafe24.sharekim93.util;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class DB {
	public Connection getConnection() {
		
		Connection conn= null;
		
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:comp/env");
			DataSource db = (DataSource) envContext.lookup("jdbc/sharekim93");
			conn = db.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
