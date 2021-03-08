package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DButil;

public class NationalMeritDDL {
	
	public static NationalMeritDDL instance = new NationalMeritDDL();
	
	public static NationalMeritDDL getInstance() {
		return instance;
	}
	
	public boolean createTable() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("CREATE TABLE NATIONALMERIT(" + 
											"MNGNO NUMBER(9) PRIMARY KEY," + 
											"NAMEKO VARCHAR2(80)," + 
											"NAMECH VARCHAR2(80)," + 
											"DIFFNAME VARCHAR2(150)," + 
											"BIRTHDAY VARCHAR2(20), " + 
											"LASTDAY VARCHAR2(20), " + 
											"SEX VARCHAR2(3)," + 
											"REGISTERLARGEDIV VARCHAR2(60)," + 
											"REGISTERMIDDIV VARCHAR2(60)," + 
											"REGISTERSMALLDIV VARCHAR2(60)," + 
											"JUDGEYEAR NUMBER(4)," + 
											"HUNKUK VARCHAR2(24)," + 
											"WORKOUTAFFIL VARCHAR2(24)," + 
											"ACHIVEMENTKO CLOB" +
											")");

			pstmt.executeUpdate();
			
		} finally {
			DButil.close(con, pstmt);
		}
		return true;
	}
		
	public boolean deleteTable() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DButil.getConnection();
			
			pstmt = con.prepareStatement("PURGE RECYCLEBIN");
			pstmt.execute();
			
			pstmt.close();
			
			pstmt = con.prepareStatement("DROP TABLE NATIONALMERIT CASCADE CONSTRAINT");
			pstmt.executeUpdate();
			
		} finally {
			DButil.close(con, pstmt);
		}
		return true;
	}
}
	
