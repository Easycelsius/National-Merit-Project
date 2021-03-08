package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BackUpDTO;
import util.DButil;

public class BackUpDAO {
	// 프로젝트에 저장
	public static boolean addBackUpDTO(BackUpDTO backUpDTO) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO backUp VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, backUpDTO.getMngNo());
			pstmt.setString(2, backUpDTO.getNameKo());
			pstmt.setString(3, backUpDTO.getNameCh());
			pstmt.setString(4, backUpDTO.getDiffName());
			pstmt.setString(5, backUpDTO.getBirthday());
			pstmt.setString(6, backUpDTO.getLastday());
			pstmt.setString(7, backUpDTO.getSex());
			pstmt.setString(8, backUpDTO.getRegisterLargeDiv());
			pstmt.setString(9, backUpDTO.getRegisterMidDiv());
			pstmt.setString(10, backUpDTO.getRegisterSmallDiv());
			pstmt.setString(11, backUpDTO.getJudgeYear());
			pstmt.setString(12, backUpDTO.getHunkuk());
			pstmt.setString(13, backUpDTO.getWorkoutAffil());
			pstmt.setString(14, backUpDTO.getAchivementKo());
			pstmt.setString(15, backUpDTO.getDeleteDate());
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DButil.close(con, pstmt);
		}
		return false;
	}

	// 관리 번호로 조회하기
	public static BackUpDTO getBackUpListFromMngno(int mngNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BackUpDTO list = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM BackUp WHERE mngno=?");
			pstmt.setInt(1, mngNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				list = new BackUpDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9),
						rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13),
						rset.getString(14), rset.getString(15));
			}
		} finally {
			DButil.close(con, pstmt, rset);
		}
		return list;
	}

	// 모든(전체) BackUp 조회
	public static ArrayList<BackUpDTO> getAllListBackUp() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BackUpDTO> list = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM BackUp");
			rset = pstmt.executeQuery();

			list = new ArrayList<BackUpDTO>();
			while (rset.next()) {
				list.add(new BackUpDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9),
						rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13),
						rset.getString(14), rset.getString(15)));
			}
		} finally {
			DButil.close(con, pstmt, rset);
		}
		return list;
	}
}
