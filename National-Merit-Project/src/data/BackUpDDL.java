package data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import util.DButil;

public class BackUpDDL {
	public static BackUpDDL instance = new BackUpDDL();

	public static BackUpDDL getInstance() {
		return instance;
	}

	// 추가된 테이블
	// 추가 : BACKUPTABLE 생성
	public boolean createBackUpTable() throws SQLException {
		Connection con = null;
		Statement pstmt = null;
		try {
			con = DButil.getConnection();
			pstmt = con.createStatement();

			pstmt.executeUpdate(
					"CREATE TABLE BACKUP(" + "MNGNO NUMBER(9)," + "NAMEKO VARCHAR2(80)," + "NAMECH VARCHAR2(80),"
							+ "DIFFNAME VARCHAR2(150)," + "BIRTHDAY VARCHAR2(20), " + "LASTDAY VARCHAR2(20), "
							+ "SEX VARCHAR2(3)," + "REGISTERLARGEDIV VARCHAR2(60)," + "REGISTERMIDDIV VARCHAR2(60),"
							+ "REGISTERSMALLDIV VARCHAR2(60)," + "JUDGEYEAR NUMBER(4)," + "HUNKUK VARCHAR2(24),"
							+ "WORKOUTAFFIL VARCHAR2(24)," + "ACHIVEMENTKO CLOB," + "DELETE_DATE DATE)");

		} finally {
			DButil.close(con, pstmt);
		}
		return true;
	}

	// BACKUPTABLE 삭제
	public boolean deleteBackUpTable() throws SQLException {
		Connection con = null;
		Statement pstmt = null;
		try {
			con = DButil.getConnection();
			pstmt = con.createStatement();
			pstmt.executeUpdate("DROP TABLE BACKUP");
			
		} finally {
			DButil.close(con, pstmt);
		}
		return true;
	}

	// CreateBackUpTrigger 구현
	public boolean createBackUpTrigger() throws SQLException {
		Connection con = null;
		Statement pstmt = null;
		try {
			con = DButil.getConnection();
			pstmt = con.createStatement();

			pstmt.execute("CREATE OR REPLACE TRIGGER BACKUP_TRIGGER "
					+ "BEFORE DELETE ON NATIONALMERIT FOR EACH ROW BEGIN INSERT INTO BACKUP VALUES(:OLD.MNGNO,"
					+ ":OLD.NAMEKO,"
					+ ":OLD.NAMECH,"
					+ ":OLD.DIFFNAME,"
					+ ":OLD.BIRTHDAY,"
					+ ":OLD.LASTDAY,"
					+ ":OLD.SEX,"
					+ ":OLD.REGISTERLARGEDIV,"
					+ ":OLD.REGISTERMIDDIV,"
					+ ":OLD.REGISTERSMALLDIV,"
					+ ":OLD.JUDGEYEAR,"
					+ ":OLD.HUNKUK,"
					+ ":OLD.WORKOUTAFFIL,"
					+ ":OLD.ACHIVEMENTKO,"
					+ "SYSDATE);"
					+ "END;");

		} finally {
			DButil.close(con, pstmt);
		}
		return true;
	}

	// dropBackUpTrigger 구현
	public boolean dropBackUpTrigger() throws SQLException {
		Connection con = null;
		Statement pstmt = null;
		try {
			con = DButil.getConnection();
			pstmt = con.createStatement();
			pstmt.execute("DROP TRIGGER BACKUP_TRIGGER");
		} finally {
			DButil.close(con, pstmt);
		}
		return true;
	}
	
}
