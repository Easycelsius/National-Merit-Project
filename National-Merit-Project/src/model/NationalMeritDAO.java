package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.NationalMeritDTO;
import util.DButil;

public class NationalMeritDAO {
	// 프로젝트 저장
	public static boolean addNMDTO(NationalMeritDTO nationalMeritDTO) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO nationalmerit VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, nationalMeritDTO.getMngNo());
			pstmt.setString(2, nationalMeritDTO.getNameKo());
			pstmt.setString(3, nationalMeritDTO.getNameCh());
			pstmt.setString(4, nationalMeritDTO.getDiffName());
			pstmt.setString(5, nationalMeritDTO.getBirthday());
			pstmt.setString(6, nationalMeritDTO.getLastday());
			pstmt.setString(7, nationalMeritDTO.getSex());
			pstmt.setString(8, nationalMeritDTO.getRegisterLargeDiv());
			pstmt.setString(9, nationalMeritDTO.getRegisterMidDiv());
			pstmt.setString(10, nationalMeritDTO.getRegisterSmallDiv());
			pstmt.setString(11, nationalMeritDTO.getJudgeYear());
			pstmt.setString(12, nationalMeritDTO.getHunkuk());
			pstmt.setString(13, nationalMeritDTO.getWorkoutAffil());
			pstmt.setString(14, nationalMeritDTO.getAchivementKo());

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
			pstmt = con.prepareStatement("DELETE FROM nationalmerit WHERE mngno=?");
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
	public static NationalMeritDTO getListFromMngno(int mngNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		NationalMeritDTO list = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE mngno=?");
			pstmt.setInt(1, mngNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				list = new NationalMeritDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9),
						rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13),
						rset.getString(14));
			}
		} finally {
			DButil.close(con, pstmt, rset);
		}
		return list;
	}

	// 유공자 이름 검색 : getListsFromNameKo()
	public static ArrayList<NationalMeritDTO> getListsFromNameKo(String nameKo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> lists = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE nameko LIKE '%' || ? || '%'");
			pstmt.setString(1, nameKo);
			rset = pstmt.executeQuery();

			lists = new ArrayList<NationalMeritDTO>();
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

	// 유공자 한자이름 검색 : getDTONameCh()
	public static ArrayList<NationalMeritDTO> getListsFromNameCh(String nameCh) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> lists = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE namech LIKE '%' || ? || '%'");
			pstmt.setString(1, nameCh);
			rset = pstmt.executeQuery();
			lists = new ArrayList<NationalMeritDTO>();
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

	// 유공자 유공자 이명 검색 : getListsFromDiffName()
	public static ArrayList<NationalMeritDTO> getListsFromDiffName(String diffName) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> lists = null;

		if (diffName.equals("null")) {
			try {
				con = DButil.getConnection();
				pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE diffname IS NULL");
				rset = pstmt.executeQuery();
				lists = new ArrayList<NationalMeritDTO>();
				while (rset.next()) {
					lists.add(new NationalMeritDTO(rset.getInt(1), rset.getString(2), rset.getString(3),
							rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7),
							rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11),
							rset.getString(12), rset.getString(13), rset.getString(14)));
				}
			} finally {
				DButil.close(con, pstmt, rset);
			}
			return lists;

		} else {
			try {
				con = DButil.getConnection();
				pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE diffname LIKE '%' || ? || '%'");
				pstmt.setString(1, diffName);
				rset = pstmt.executeQuery();
				lists = new ArrayList<NationalMeritDTO>();
				while (rset.next()) {
					lists.add(new NationalMeritDTO(rset.getInt(1), rset.getString(2), rset.getString(3),
							rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7),
							rset.getString(8), rset.getString(9), rset.getString(10), rset.getString(11),
							rset.getString(12), rset.getString(13), rset.getString(14)));
				}
			} finally {
				DButil.close(con, pstmt, rset);
			}
			return lists;
		}
	}

	// 유공자 성별로 검색 : getListsFromSex();
	public static ArrayList<NationalMeritDTO> getListsFromSex(String sex) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> lists = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE sex = ?");
			pstmt.setString(1, sex);
			rset = pstmt.executeQuery();
			lists = new ArrayList<NationalMeritDTO>();
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

	// 유공자 본적대분류로 검색 : getListsFromRegisterLargeDiv();
	public static ArrayList<NationalMeritDTO> getListsFromRegisterLargeDiv(String registerLargeDiv)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> lists = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE registerlargediv = ?");
			pstmt.setString(1, registerLargeDiv);
			rset = pstmt.executeQuery();
			lists = new ArrayList<NationalMeritDTO>();
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

	// 유공자 본적중분류로 검색 : getListsFromRegisterMidDiv();
	public static ArrayList<NationalMeritDTO> getListsFromRegisterMidDiv(String registerMidDiv) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> lists = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE registermiddiv = ?");
			pstmt.setString(1, registerMidDiv);
			rset = pstmt.executeQuery();
			lists = new ArrayList<NationalMeritDTO>();
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

	// 유공자 본적소분류로 검색 : getListsFromRegisterSmallDiv();
	public static ArrayList<NationalMeritDTO> getListsFromRegisterSmallDiv(String registerSmallDiv)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> lists = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE registersmalldiv = ?");
			pstmt.setString(1, registerSmallDiv);
			rset = pstmt.executeQuery();
			lists = new ArrayList<NationalMeritDTO>();
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

	// 유공자 포상년도 검색 (년도로검색) : getListsFromIudgeYear();
	public static ArrayList<NationalMeritDTO> getListsFromJudgeYear(String judgeYear) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> lists = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE judgeyear = ?");
			pstmt.setString(1, judgeYear);
			rset = pstmt.executeQuery();
			lists = new ArrayList<NationalMeritDTO>();
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

	// 유공자 훈격으로 검색 : getListsFromHunkuk();
	public static ArrayList<NationalMeritDTO> getListsFromHunkuk(String hunkuk) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> lists = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE hunkuk = ?");
			pstmt.setString(1, hunkuk);
			rset = pstmt.executeQuery();
			lists = new ArrayList<NationalMeritDTO>();
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

	// 유공자 운동계열로 검색 : getListsFromWorkoutAffil();
	public static ArrayList<NationalMeritDTO> getListsFromWorkoutAffil(String workoutAffil) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> lists = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE workoutaffil = ?");
			pstmt.setString(1, workoutAffil);
			rset = pstmt.executeQuery();
			lists = new ArrayList<NationalMeritDTO>();
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

	// 모든(전체) 조회
	public static ArrayList<NationalMeritDTO> getLists() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> list = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit");
			rset = pstmt.executeQuery();

			list = new ArrayList<NationalMeritDTO>();
			while (rset.next()) {
				list.add(new NationalMeritDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9),
						rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13),
						rset.getString(14)));
			}

		} finally {
			DButil.close(con, pstmt, rset);
		}
		return list;
	}

	// 유공자공적개요 국한문 병기 내 키워드 검색
	public static ArrayList<NationalMeritDTO> getListsFromWord(String word) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> list = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE achivementko LIKE '%' || ? || '%'");
			pstmt.setString(1, word);

			rset = pstmt.executeQuery();

			list = new ArrayList<NationalMeritDTO>();
			while (rset.next()) {
				list.add(new NationalMeritDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9),
						rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13),
						rset.getString(14)));
			}
		} finally {
			DButil.close(con, pstmt, rset);
		}

		return list;
	}

	// update
	public static boolean updateNationalMerit(NationalMeritDTO nationalMeritDTO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DButil.getConnection(); //
			pstmt = con.prepareStatement(
					"UPDATE nationalmerit SET nameko = ?, namech = ?, diffname = ?, birthday = ?, lastday = ?, sex = ?, registerlargediv = ?, registermiddiv = ?, registersmalldiv = ?, judgeyear = ?, hunkuk = ?, workoutaffil = ?, achivementko = ? WHERE mngno = ?");
			pstmt.setString(1, nationalMeritDTO.getNameKo());
			pstmt.setString(2, nationalMeritDTO.getNameCh());
			pstmt.setString(3, nationalMeritDTO.getDiffName());
			pstmt.setString(4, nationalMeritDTO.getBirthday());
			pstmt.setString(5, nationalMeritDTO.getLastday());
			pstmt.setString(6, nationalMeritDTO.getSex());
			pstmt.setString(7, nationalMeritDTO.getRegisterLargeDiv());
			pstmt.setString(8, nationalMeritDTO.getRegisterMidDiv());
			pstmt.setString(9, nationalMeritDTO.getRegisterSmallDiv());
			pstmt.setString(10, nationalMeritDTO.getJudgeYear());
			pstmt.setString(11, nationalMeritDTO.getHunkuk());
			pstmt.setString(12, nationalMeritDTO.getWorkoutAffil());
			pstmt.setString(13, nationalMeritDTO.getAchivementKo());
			pstmt.setInt(14, nationalMeritDTO.getMngNo());

			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DButil.close(con, pstmt);
		}
		return false;
	}

	// NationMeritDAO에서 birthday 미상일때 조회하기
	public static ArrayList<NationalMeritDTO> getListsFromBirthDay(String birthday) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> list = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE birthday LIKE '%' || ? || '%'");
			pstmt.setString(1, birthday);

			rset = pstmt.executeQuery();

			list = new ArrayList<NationalMeritDTO>();
			while (rset.next()) {
				list.add(new NationalMeritDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9),
						rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13),
						rset.getString(14)));
			}
		} finally {
			DButil.close(con, pstmt, rset);
		}
		return list;
	}

	/// NationMeritDAO에서 lastday 미상일때 조회하기
	public static ArrayList<NationalMeritDTO> getListsFromLastDay(String lastday) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NationalMeritDTO> list = null;

		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM nationalmerit WHERE lastday LIKE '%' || ? || '%'");
			pstmt.setString(1, lastday);

			rset = pstmt.executeQuery();

			list = new ArrayList<NationalMeritDTO>();
			while (rset.next()) {
				list.add(new NationalMeritDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), rset.getString(9),
						rset.getString(10), rset.getString(11), rset.getString(12), rset.getString(13),
						rset.getString(14)));
			}
		} finally {
			DButil.close(con, pstmt, rset);
		}
		return list;
	}

}
