package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.NationalMeritDTO;
import util.DButil;

public class MultiTableDAO {
	// 년도로 생애시기를 조회하기 getPeriodFromDate()
	public static ArrayList<NationalMeritDTO> getPeriodFromDate(String date) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> lists = new ArrayList<NationalMeritDTO>();

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("Select M.mngNo, M.nameKo, M.nameCH, M.diffName,"
					+ " M.birthday, M.lastday, M.sex, M.registerLargeDiv, M.registerMidDiv, M.registerSmallDiv,"
					+ " M.judgeYear, M.hunkuk, M.workoutAffil, M.achivementKo"
					+ " FROM BIRTH_AND_DEATH B, NATIONALMERIT M" + " WHERE B.pro_birthday <= ? AND ? <= B.pro_lastday"
					+ " AND B.mngNo = M.mngNO");

			pstmt.setString(1, date); // "1950-06-25"
			pstmt.setString(2, date);
			rset = pstmt.executeQuery();

			while (rset.next()) {

				lists.add(new NationalMeritDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9),
						rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13),
						rset.getString(14)));
			}

		} finally {
			DButil.close(con, pstmt, rset);
		}

		return lists;
	}

	// 다중 검색 getListsInDetail(NationalMeritDTO nationalMeritDTO)
	public static ArrayList<NationalMeritDTO> getListsInDetail(NationalMeritDTO nationalMeritDTO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> lists = new ArrayList<NationalMeritDTO>();

		try {			
			con = DButil.getConnection();
			pstmt = con.prepareStatement("Select mngNo, nameKo, nameCH, diffName,"
					+ " birthday, lastday, sex, registerLargeDiv, registerMidDiv, registerSmallDiv,"
					+ " judgeYear, hunkuk, workoutAffil, achivementKo"
					+ " FROM NATIONALMERIT" 
					+ " WHERE 1=1"
					+ nationalMeritDTO.getNameKo()
					+ nationalMeritDTO.getNameCh()
					+ nationalMeritDTO.getDiffName()
					+ nationalMeritDTO.getBirthday()
					+ nationalMeritDTO.getLastday()
					+ nationalMeritDTO.getSex()
					+ nationalMeritDTO.getRegisterLargeDiv()
					+ nationalMeritDTO.getRegisterMidDiv()
					+ nationalMeritDTO.getRegisterSmallDiv()
					+ nationalMeritDTO.getJudgeYear()
					+ nationalMeritDTO.getHunkuk()
					+ nationalMeritDTO.getWorkoutAffil()
					+ nationalMeritDTO.getAchivementKo()
					);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {

				lists.add(new NationalMeritDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9),
						rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13),
						rset.getString(14)));
			}

		} finally {
			DButil.close(con, pstmt, rset);
		}

		return lists;
	}
}
