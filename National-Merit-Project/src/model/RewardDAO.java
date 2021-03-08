package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.RewardDTO;
import util.DButil;

public class RewardDAO {

	// 메달정보 조회 하기 getListFromMedal()
	public static RewardDTO getListFromMedal(String medal) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		RewardDTO list = null;
		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM reward WHERE MEDAL = ?");
			pstmt.setString(1, medal);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				list = new RewardDTO(rset.getString(1), rset.getString(2));
			}
		} finally {
			DButil.close(con, pstmt, rset);
		}
		return list;
	}

	// 전체 조회 : getAllListreward(0)
	public static ArrayList<RewardDTO> getAllListreward() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<RewardDTO> list = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM reward");
			rset = pstmt.executeQuery();

			list = new ArrayList<RewardDTO>();
			while (rset.next()) {
				list.add(new RewardDTO(rset.getString(1), rset.getString(2)));
			}
		} finally {
			DButil.close(con, pstmt, rset);
		}
		return list;
	}

	// 메달 삭제하기
	public static boolean deleteFromMedal(String medal) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("delete from reward where medal=?");
			pstmt.setString(1, medal);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DButil.close(con, pstmt);
		}
		return false;
	}

	// 메달 추가하기
	public static boolean InsertFromMedal(String medal, String explanation) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO Reward VALUES(?,?)");
			pstmt.setString(1, medal);
			pstmt.setString(2, explanation);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DButil.close(con, pstmt);
		}
		return false;
	}
}
