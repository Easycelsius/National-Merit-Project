package model;

import java.sql.SQLException;
import java.util.ArrayList;

import data.DataCollector;
import dto.BackUpDTO;
import dto.BirthAndDeathDTO;
import dto.NationalMeritDTO;
import dto.RewardDTO;
import exception.NotExistException;
import util.CalenderUtil;

public class DMLService {
	private static DMLService instance = new DMLService();
	private DataCollector dataCollector = DataCollector.getInstance();
	private CalenderUtil myCalender = CalenderUtil.getInstance();

	private DMLService() {
	}

	public static DMLService getInstance() {
		return instance;
	}

	// NationalMeritDAO 관련
	// 관리번호가 없을시 에러 메소드
	public void notExistMngNo(int mngNo) throws SQLException {
		NationalMeritDTO nationalMeritDTO = NationalMeritDAO.getListFromMngno(mngNo);
		if (nationalMeritDTO == null) {
			throw new SQLException("관리번호가 존재하지 않습니다.");
		}
	}

	// data -> Insert
	public boolean initInsertData() throws SQLException, InterruptedException {
		ArrayList<NationalMeritDTO> lists = dataCollector.startCrwaling();

		for (NationalMeritDTO list : lists) {
			// sql 과부하 방지 : 프로세스 수 조절
			Thread.sleep(100);

			// NationalMerit 테이블에 삽입하기
			if (doesDataExist(list.getMngNo()) == false) {

				NationalMeritDAO.addNMDTO(list);
				System.out.println(list);

				// BIRTH_AND_DEATH 테이블에 삽입하기
				addBADDAO(list);
			}
		}

		return false;
	}

	// 새로운 데이터 추가하기
	public boolean insertData(NationalMeritDTO nationalMeritDTO) throws SQLException {
		NationalMeritDAO.addNMDTO(nationalMeritDTO);
		return addBADDAO(nationalMeritDTO);
	}

	// 관리번호로 조회
	public NationalMeritDTO searchByMngno(int mngNo) throws SQLException {
		return NationalMeritDAO.getListFromMngno(mngNo);
	}

	// 전체 조회하기
	public ArrayList<NationalMeritDTO> searchAll() throws SQLException {
		return NationalMeritDAO.getLists();
	}

	// 유공자 이름으로 검색
	public ArrayList<NationalMeritDTO> searchByNameKo(String nameKo) throws SQLException {
		return NationalMeritDAO.getListsFromNameKo(nameKo);
	}

	// 유공자 한자이름으로 검색
	public ArrayList<NationalMeritDTO> searchByNameCh(String nameCh) throws SQLException {
		return NationalMeritDAO.getListsFromNameCh(nameCh);
	}

	// 유공자 이명 검색
	public ArrayList<NationalMeritDTO> searchByDiffName(String diffName) throws SQLException {
		return NationalMeritDAO.getListsFromDiffName(diffName);
	}

	// 유공자 성별 검색
	public ArrayList<NationalMeritDTO> searchBySex(String sex) throws SQLException {
		return NationalMeritDAO.getListsFromSex(sex);
	}

	// 유공자 본적대분류 검색
	public ArrayList<NationalMeritDTO> searchByRegisterLargeDiv(String registerLargeDiv) throws SQLException {
		return NationalMeritDAO.getListsFromRegisterLargeDiv(registerLargeDiv);
	}

	// 유공자 본적중분류 검색
	public ArrayList<NationalMeritDTO> searchByRegisterMidDiv(String registerMidDiv) throws SQLException {
		return NationalMeritDAO.getListsFromRegisterMidDiv(registerMidDiv);
	}

	// 유공자 본적소분류 검색
	public ArrayList<NationalMeritDTO> searchByRegisterSmallDiv(String registerSmallDiv) throws SQLException {
		return NationalMeritDAO.getListsFromRegisterSmallDiv(registerSmallDiv);
	}

	// 유공자 포상년도 검색
	public ArrayList<NationalMeritDTO> searchByJudgeYear(String judgeYear) throws SQLException {
		return NationalMeritDAO.getListsFromJudgeYear(judgeYear);
	}

	// 유공자 훈격 검색
	public ArrayList<NationalMeritDTO> searchByHunkuk(String hunkuk) throws SQLException {
		return NationalMeritDAO.getListsFromHunkuk(hunkuk);
	}

	// 유공자 운동계열 검색
	public ArrayList<NationalMeritDTO> searchByWorkoutAffil(String workoutAffil) throws SQLException {
		return NationalMeritDAO.getListsFromWorkoutAffil(workoutAffil);
	}

	// 유공자 원하는 공적개요국한문 병기 원하는단어로 검색하기
	public ArrayList<NationalMeritDTO> searchByAchivementKo(String word) throws SQLException {
		return NationalMeritDAO.getListsFromWord(word);
	}

	// NationMeritDAO에서 birthday 미상일때 조회하기
	public ArrayList<NationalMeritDTO> searchByBirthday(String birthday) throws SQLException {
		return NationalMeritDAO.getListsFromBirthDay(birthday);
	}

	/// NationMeritDAO에서 lastday 미상일때 조회하기
	public ArrayList<NationalMeritDTO> searchByLastday(String lastday) throws SQLException {
		return NationalMeritDAO.getListsFromLastDay(lastday);
	}

	// NationMeritDAO 상세검색 기능
	public ArrayList<NationalMeritDTO> searchInDetail(NationalMeritDTO nationalMeritDTO) throws SQLException {

		if (nationalMeritDTO.getNameKo().equals("N/A") || nationalMeritDTO.getNameKo().equals("")
				|| nationalMeritDTO.getNameKo() == null) {
			nationalMeritDTO.setNameKo(" And 1=1");
		} else {
			nationalMeritDTO.setNameKo(" AND nameko LIKE '%" + String.format("%s", nationalMeritDTO.getNameKo()) + "%'");
		}

		if (nationalMeritDTO.getNameCh().equals("N/A") || nationalMeritDTO.getNameCh().equals("")
				|| nationalMeritDTO.getNameCh() == null) {
			nationalMeritDTO.setNameCh(" AND 1=1");
		} else {
			nationalMeritDTO.setNameCh(" AND namech LIKE '%" + String.format("%s", nationalMeritDTO.getNameCh()) + "%'");
		}

		if (nationalMeritDTO.getDiffName().equals("N/A") || nationalMeritDTO.getDiffName().equals("")
				|| nationalMeritDTO.getDiffName() == null) {
			nationalMeritDTO.setDiffName(" AND 1=1");
		} else {
			nationalMeritDTO.setDiffName(" AND diffName LIKE '%" + String.format("%s", nationalMeritDTO.getDiffName()) + "%'");
		}

		if (nationalMeritDTO.getBirthday().equals("N/A") || nationalMeritDTO.getBirthday().equals("")
				|| nationalMeritDTO.getBirthday() == null) {
			nationalMeritDTO.setBirthday(" AND 1=1");
		} else {
			nationalMeritDTO.setBirthday(" AND birthday LIKE '%" + String.format("%s", nationalMeritDTO.getBirthday()) + "%'");
		}

		if (nationalMeritDTO.getLastday().equals("N/A") || nationalMeritDTO.getLastday().equals("")
				|| nationalMeritDTO.getLastday() == null) {
			nationalMeritDTO.setLastday(" AND 1=1");
		} else {
			nationalMeritDTO.setLastday(" AND lastday LIKE '%" + String.format("%s", nationalMeritDTO.getLastday()) + "%'");
		}

		if (nationalMeritDTO.getSex().equals("N/A") || nationalMeritDTO.getSex().equals("")
				|| nationalMeritDTO.getSex() == null) {
			nationalMeritDTO.setSex(" AND 1=1");
		} else {
			nationalMeritDTO.setSex(String.format(" AND sex = '%s'", nationalMeritDTO.getSex()));
		}

		if (nationalMeritDTO.getRegisterLargeDiv().equals("N/A") || nationalMeritDTO.getRegisterLargeDiv().equals("")
				|| nationalMeritDTO.getRegisterLargeDiv() == null) {
			nationalMeritDTO.setRegisterLargeDiv(" AND 1=1");
		} else {
			nationalMeritDTO.setRegisterLargeDiv(String.format(" AND registerLargeDiv = '%s'", nationalMeritDTO.getRegisterLargeDiv()));
		}

		if (nationalMeritDTO.getRegisterMidDiv().equals("N/A") || nationalMeritDTO.getRegisterMidDiv().equals("")
				|| nationalMeritDTO.getRegisterMidDiv() == null) {
			nationalMeritDTO.setRegisterMidDiv(" AND 1=1");
		} else {
			nationalMeritDTO.setRegisterMidDiv(String.format(" AND registerMidDiv = '%s'", nationalMeritDTO.getRegisterMidDiv()));
		}

		if (nationalMeritDTO.getRegisterSmallDiv().equals("N/A") || nationalMeritDTO.getRegisterSmallDiv().equals("")
				|| nationalMeritDTO.getRegisterSmallDiv() == null) {
			nationalMeritDTO.setRegisterSmallDiv(" AND 1=1");
		} else {
			nationalMeritDTO.setRegisterSmallDiv(String.format(" AND registerSmallDiv = '%s'", nationalMeritDTO.getRegisterSmallDiv()));
		}

		if (nationalMeritDTO.getJudgeYear().equals("N/A") || nationalMeritDTO.getJudgeYear().equals("")
				|| nationalMeritDTO.getJudgeYear() == null) {
			nationalMeritDTO.setJudgeYear(" AND 1=1");
		} else {
			nationalMeritDTO.setJudgeYear(String.format(" AND judgeYear = '%s'", nationalMeritDTO.getJudgeYear()));
		}

		if (nationalMeritDTO.getHunkuk().equals("N/A") || nationalMeritDTO.getHunkuk().equals("")
				|| nationalMeritDTO.getHunkuk() == null) {
			nationalMeritDTO.setHunkuk(" AND 1=1");
		} else {
			nationalMeritDTO.setHunkuk(String.format(" AND hunkuk = '%s'", nationalMeritDTO.getHunkuk()));
		}

		if (nationalMeritDTO.getWorkoutAffil().equals("N/A") || nationalMeritDTO.getWorkoutAffil().equals("")
				|| nationalMeritDTO.getWorkoutAffil() == null) {
			nationalMeritDTO.setWorkoutAffil(" AND 1=1");
		} else {
			nationalMeritDTO.setWorkoutAffil(String.format(" AND workoutAffil = '%s'", nationalMeritDTO.getWorkoutAffil()));
		}

		if (nationalMeritDTO.getAchivementKo().equals("N/A") || nationalMeritDTO.getAchivementKo().equals("")
				|| nationalMeritDTO.getAchivementKo() == null) {
			nationalMeritDTO.setAchivementKo(" AND 1=1");
		} else {
			nationalMeritDTO.setAchivementKo(" AND achivementKo LIKE '%" + String.format("%s", nationalMeritDTO.getAchivementKo()) + "%'");
		}

		return MultiTableDAO.getListsInDetail(nationalMeritDTO);
	}

	// mngNo로 삭제하기
	public boolean deleteMngNo(int mngNo) throws SQLException {
		return NationalMeritDAO.deleteFromMngno(mngNo);
	}

	// MultiTableDAO
	// 날짜로 당시 생존한 사람 검색하기
	public ArrayList<NationalMeritDTO> findSurvivorsByDate(String date) throws SQLException {
		String str1 = date;
		int len1 = str1.length();
		switch (len1) {

			case 0:
				str1 = null;
				break;
			// 연도만 있는 경우
			case 4:
				str1 = str1 + "0101";
				break;
			// 연월만 있는 경우
			case 6:
				str1 = "01";
				break;
	
			case 8:
				if (str1.substring(4).equals("0230") || str1.substring(4).equals("0229")) {
					str1 = str1.substring(0, 6) + myCalender.findLastDayInMonth(Integer.parseInt(str1.substring(0, 4)),
							Integer.parseInt(str1.substring(4, 6)));
				}
	
				// 유효값 검증
				if (12 < Integer.parseInt(str1.substring(4, 6))) {
	
					str1 = str1.substring(0, 4) + "12" + "31";
				}
	
				if (Integer.parseInt(str1) >= Integer.parseInt(str1.substring(0, 6) + myCalender.findLastDayInMonth(
						Integer.parseInt(str1.substring(0, 4)), Integer.parseInt(str1.substring(4, 6))))) {
	
					str1 = str1.substring(0, 4) + "-" + str1.substring(4, 6) + "-" + myCalender.findLastDayInMonth(
							Integer.parseInt(str1.substring(0, 4)), Integer.parseInt(str1.substring(4, 6)));
				}
			}
		return MultiTableDAO.getPeriodFromDate(str1);
	}

	// BirthAndDeathDAO
	// BAD 테이블 내용 추가하기
	public boolean addBADDAO(NationalMeritDTO list) throws SQLException {
		// Birth setting
		String str1 = list.getBirthday().replaceAll("[^0-9]", "");
		int len1 = str1.length();

		switch (len1) {
			// 정보가 없는 경우
			case 0:
				list.setBirthday(null);
				break;
			// 연도만 있는 경우
			case 4:
				list.setBirthday(str1 + "0101");
				break;
			// 연월만 있는 경우
			case 6:
				list.setBirthday(str1 + "01");
				break;
			// 정상적인 경우
			case 8:
				if (str1.substring(4).equals("0230") || str1.substring(4).equals("0229")) {
					str1 = str1.substring(0, 6) + myCalender.findLastDayInMonth(Integer.parseInt(str1.substring(0, 4)),
							Integer.parseInt(str1.substring(4, 6)));
				}
	
				// 특별한 상황1 - 김덕장님
				if (str1.equals("18750559")) {
					str1 = "18750509";
				}
	
				// 특별한 상황2 - 박태열님
				if (str1.substring(4).equals("0000")) {
					str1 = str1.substring(0, 4) + "0101";
				}
				
				// 유효값 검증
				if (12 < Integer.parseInt(str1.substring(4, 6))) {
					str1 = str1.substring(0, 4) + "12" + "31";
				}
	
				// 유효값 검증
				if (Integer.parseInt(str1) >= Integer.parseInt(str1.substring(0, 6) + myCalender.findLastDayInMonth(
						Integer.parseInt(str1.substring(0, 4)), Integer.parseInt(str1.substring(4, 6))))) {
					str1 = str1.substring(0, 6) + myCalender.findLastDayInMonth(Integer.parseInt(str1.substring(0, 4)),
							Integer.parseInt(str1.substring(4, 6)));
				}
	
				list.setBirthday(str1);
				break;
		}
		;

		// Lastday setting
		String str2 = list.getLastday().replaceAll("[^0-9]", "");
		int len2 = str2.length();

		switch (len2) {
			// 정보가 없는 경우
			case 0:
				list.setLastday(null);
				break;
			// 연도만 있는 경우
			case 4:
				list.setLastday(str2 + "12" + "31");
				break;
			// 연월만 있는 경우
			case 6:
				list.setLastday(str2 + myCalender.findLastDayInMonth(Integer.parseInt(str2.substring(0, 4)),
						Integer.parseInt(str2.substring(4, 6))));
				break;
			// 정상적인 경우
			case 8:
				// 특별한 상황1 - 김일환님
				if (str2.equals("19501131")) {
					str2 = "19501130";
				}
	
				if (str2.substring(4).equals("0230") || str2.substring(4).equals("0229")) {
					str2 = str2.substring(0, 6) + myCalender.findLastDayInMonth(Integer.parseInt(str2.substring(0, 4)),
							Integer.parseInt(str2.substring(4, 6)));
				}
	
				if (str2.substring(4).equals("0000")) {
					str2 = str2.substring(0, 4) + "12" + "31";
				}
				
				// 유효값 검증
				if (12 < Integer.parseInt(str2.substring(4, 6))) {
					str2 = str2.substring(0, 4) + "12" + "31";
				}
	
				// 유효값 검증
				if (Integer.parseInt(str2) >= Integer.parseInt(str2.substring(0, 6) + myCalender.findLastDayInMonth(
						Integer.parseInt(str2.substring(0, 4)), Integer.parseInt(str2.substring(4, 6))))) {
					str2 = str2.substring(0, 6) + myCalender.findLastDayInMonth(Integer.parseInt(str2.substring(0, 4)),
							Integer.parseInt(str2.substring(4, 6)));
				}
	
				list.setLastday(str2);
				break;
		}

		// finally append to DB
		System.out.println(new BirthAndDeathDTO(list.getMngNo(), list.getBirthday(), list.getLastday()));
		return BirthAndDeathDAO.addBADDTO(new BirthAndDeathDTO(list.getMngNo(), list.getBirthday(), list.getLastday()));
	}

	// BAD 테이블 내 관리번호로 삭제하기
	public boolean deleteMngNoAtBADDAO(int mngNo) throws SQLException {
		return BirthAndDeathDAO.deleteFromMngno(mngNo);
	}

	// BAD 테이블 내 관리번호로 조회하기
	public BirthAndDeathDTO searchByMngnoAtBADDAO(int mngNo) throws SQLException {
		return BirthAndDeathDAO.getListFromMngno(mngNo);
	}

	// BAD 테이블 전체 조회
	public ArrayList<BirthAndDeathDTO> searchAllBAD() throws SQLException {
		return BirthAndDeathDAO.getListsBAD();
	}

	// 내부 활용 메소드
	// 데이터 존재 확인
	public boolean doesDataExist(int mngNo) throws SQLException {
		if (NationalMeritDAO.getListFromMngno(mngNo) != null) {
			return true;
		}
		return false;
	}

	// 관리번호로 조회한 데이터 가져오기
	public NationalMeritDTO retriveDataByMngNo(int mngNo) throws SQLException, NotExistException {
		NationalMeritDTO NationalMeritDTOData = NationalMeritDAO.getListFromMngno(mngNo);
		if (NationalMeritDTOData == null) {
			throw new NotExistException("조회하신 데이터를 찾을 수 없습니다");
		}
		return NationalMeritDTOData;
	}

	// 추가 : 훈장 정보 조회 하기
	public RewardDTO searchByReward(String medal) throws SQLException {
		return RewardDAO.getListFromMedal(medal);
	}

	// 추가 : 훈장 전체 조회하기
	public ArrayList<RewardDTO> searchByAllReward() throws SQLException {
		return RewardDAO.getAllListreward();
	}

	// 추가 : 훈장 삭제
	public boolean deleteMedal(String medal) throws SQLException {
		return RewardDAO.deleteFromMedal(medal);
	}

	// 추가 : 훈장 추가하기
	public boolean InsertMedal(String medal, String explanation) throws SQLException {
		return RewardDAO.InsertFromMedal(medal, explanation);
	}

	// 추가 : 백업 관리번호 조회하기
	public BackUpDTO searchByBackUp(int mngNo) throws SQLException {
		return BackUpDAO.getBackUpListFromMngno(mngNo);
	}

	// 추가 : 백업 전체 조회하기
	public ArrayList<BackUpDTO> searchByAllBackUp() throws SQLException {
		return BackUpDAO.getAllListBackUp();
	}
}
