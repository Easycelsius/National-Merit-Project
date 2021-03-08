package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DButil;

public class BirthAndDeathDDL {

	public static BirthAndDeathDDL instance = new BirthAndDeathDDL();

	public static BirthAndDeathDDL getInstance() {
		return instance;
	}

	// DAB 테이블 생성
	public boolean createTable() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DButil.getConnection();
					
			pstmt = con.prepareStatement("CREATE TABLE BIRTH_AND_DEATH("
					+ "MNGNO NUMBER(9) CONSTRAINT BAD_MNGNO_FK REFERENCES NATIONALMERIT(MNGNO),"
					+ "PRO_BIRTHDAY DATE,"
					+ "PRO_LASTDAY DATE)");

			pstmt.execute();

		} finally {
			DButil.close(con, pstmt);
		}
		return true;
	}

	// DAB 테이블 삭제
	public boolean deleteTable() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("DROP TABLE BIRTH_AND_DEATH CASCADE CONSTRAINT");
			pstmt.execute();

		} finally {
			DButil.close(con, pstmt);
		}
		return true;
	}

}
