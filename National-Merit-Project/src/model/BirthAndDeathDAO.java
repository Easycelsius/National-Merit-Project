package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BirthAndDeathDTO;
import util.DButil;

public class BirthAndDeathDAO {
	// 프로젝트 저장
	public static boolean addBADDTO(BirthAndDeathDTO birthAndDeathDTO) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO BIRTH_AND_DEATH VALUES(?, ?, ?)");
			pstmt.setInt(1, birthAndDeathDTO.getMngNo());
			pstmt.setString(2, birthAndDeathDTO.getProBirthday());
			pstmt.setString(3, birthAndDeathDTO.getProLastday());

			int result = pstmt.executeUpdate();

			if (result == 1) {
				return true;
			}

		} finally {
			DButil.close(con, pstmt);
		}
		return false;
	}

	// 관리번호로 삭제하기
	public static boolean deleteFromMngno(int mngNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("delete from BIRTH_AND_DEATH where mngNo=?");
			pstmt.setInt(1, mngNo);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DButil.close(con, pstmt);
		}
		return false;
	}

	// 관리번호로 조회하기 getListFromMngno()
	public static BirthAndDeathDTO getListFromMngno(int mngNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BirthAndDeathDTO list = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("Select * from BIRTH_AND_DEATH where mngNo=?");
			pstmt.setInt(1, mngNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				list = new BirthAndDeathDTO(rset.getInt(1), rset.getString(2), rset.getString(3));
			}
		} finally {
			DButil.close(con, pstmt, rset);
		}
		return list;
	}

	// BAD 테이블 전체 조회
	public static ArrayList<BirthAndDeathDTO> getListsBAD() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BirthAndDeathDTO> list = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM BIRTH_AND_DEATH");
			rset = pstmt.executeQuery();

			list = new ArrayList<BirthAndDeathDTO>();
			while (rset.next()) {
				list.add(new BirthAndDeathDTO(rset.getInt(1), rset.getString(2), rset.getString(3)));
			}

		} finally {
			DButil.close(con, pstmt, rset);
		}

		return list;
	}

}
