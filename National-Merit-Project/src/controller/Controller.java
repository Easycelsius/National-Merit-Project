package controller;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import dto.NationalMeritDTO;
import exception.NotExistException;
import model.DDLService;
import model.DMLService;
import view.EndView;
import view.SuccessView;

public class Controller {

	private Controller() {
	}

	public static Controller instance = new Controller();

	public static Controller getInstance() {
		return instance;
	}

	public DMLService dmlService = DMLService.getInstance();
	public DDLService ddlService = DDLService.getInstance();

	// ddlService
	public void createTables() {
		try {
			ddlService.createNMTable();
			ddlService.createBADTable();
			ddlService.createRewardTable();
			ddlService.createBackUpTable();
			SuccessView.showSuccess("필요한 테이블 생성 완료!");
		} catch (SQLException e) {
			EndView.showError("테이블 생성 실패!");
		}
	}

	// dmlService
	// 초기 데이터 집어넣기
	public void initInsertData() {
		try {
			try {
				dmlService.initInsertData();
			} catch (InterruptedException e) {
			}
			SuccessView.showSuccess("초기 데이터 삽입 완료!");
		} catch (SQLException e) {
			EndView.showError("초기 데이터 삽입 실패!");
		} catch (Exception e) {
			EndView.showError("초기 데이터 삽입 실패!");
		}
	}

	// 관리번호로 데이터 추가하기
	public void insertData(NationalMeritDTO nationalMeritDTO) {
		try {
			dmlService.insertData(nationalMeritDTO);
			SuccessView.showSuccess("관리번호로 정보 추가 성공!");
		} catch (SQLException e) {
			EndView.showError("관리번호로 정보 추가 실패!");
		}
	}

	// 전체 데이터 관리번호로 조회하기
	public void searchByMngno(int mngNo) {
		try {
			EndView.projectView(dmlService.retriveDataByMngNo(mngNo));
			SuccessView.showSuccess("일치하는 결과를 찾았습니다"); // 일치하는 개수도 표현하면 좋을 것 같음
		} catch (SQLException e) {
			EndView.showError("데이터베이스 관련 오류가 발생하였습니다");
		} catch (NotExistException e) {
			EndView.showError("해당 데이터를 찾을 수 없습니다");
		}
	}

	// 전체 데이터 그냥 조회하기
	public void searchAll() {
		try {
			EndView.projectListView(dmlService.searchAll());
		} catch (SQLException e) {
			EndView.showError("전체 데이터 조회 실패!");
		}
	}

	// 유공자 이름 조회하기
	public void searchByNameKo(String nameKo) {
		try {
			EndView.projectListView(dmlService.searchByNameKo(nameKo));
		} catch (SQLException e) {
			EndView.showError("유공자 이름 전체 데이터 조회 실패!");
		}
	}

	// 유공자 한자 이름 조회하기
	public void searchByNameCh(String nameCh) {
		try {
			EndView.projectListView(dmlService.searchByNameCh(nameCh));
		} catch (SQLException e) {
			EndView.showError("유공자 한자이름 전체 데이터 조회 실패!");
		}
	}

	/// 유공자 이명 검색
	public void searchByDiffName(String diffName) {
		try {
			EndView.projectListView(dmlService.searchByDiffName(diffName));
		} catch (SQLException e) {
			EndView.showError("유공자 이명 이름 전체 데이터 조회 실패!");
		}
	}

	// 유공자 성별 검색
	public void searchBySex(String sex) {
		try {
			EndView.projectListView(dmlService.searchBySex(sex));
		} catch (SQLException e) {
			EndView.showError("유공자 성별 전체 데이터 조회 실패!");
		}
	}

	// 유공자 본적대분류 검색
	public void searchByRegisterLargeDiv(String registerLargeDiv) {
		try {
			EndView.projectListView(dmlService.searchByRegisterLargeDiv(registerLargeDiv));
		} catch (SQLException e) {
			EndView.showError("유공자 본적대분류 조회 실패!");
		}
	}

	// 유공자 본적중분류 검색
	public void searchByRegisterMidDiv(String registerMidDiv) {
		try {
			EndView.projectListView(dmlService.searchByRegisterMidDiv(registerMidDiv));
		} catch (SQLException e) {
			EndView.showError("유공자 본적중분류 조회 실패!");
		}
	}

	// 유공자 본적소분류 검색
	public void searchByRegisterSmallDiv(String registerSmallDiv) {
		try {
			EndView.projectListView(dmlService.searchByRegisterSmallDiv(registerSmallDiv));
		} catch (SQLException e) {
			EndView.showError("유공자 본적소분류 조회 실패!");
		}
	}

	// 유공자 포상년도 검색
	public void searchByJudgeYear(String judgeYear) {
		try {
			EndView.projectListView(dmlService.searchByJudgeYear(judgeYear));
		} catch (SQLException e) {
			EndView.showError("유공자 포상년도 조회 실패!");
		}
	}

	// 유공자 훈격 검색
	public void searchByHunkuk(String hunkuk) {
		try {
			EndView.projectListView(dmlService.searchByHunkuk(hunkuk));
		} catch (SQLException e) {
			EndView.showError("유공자 훈격 조회 실패!");
		}
	}

	// 유공자 운동계열 검색
	public void searchByWorkoutAffil(String workoutAffil) {
		try {
			EndView.projectListView(dmlService.searchByWorkoutAffil(workoutAffil));
		} catch (SQLException e) {
			EndView.showError("유공자 운동계열 조회 실패!");
		}
	}

	// 유공자 원하는 공적개요국한문 병기 원하는단어로 검색하기
	public void searchByAchivementKo(String word) {
		try {
			EndView.projectListView(dmlService.searchByAchivementKo(word));
		} catch (SQLException e) {
			EndView.showError("유공자 원하는 단어로 검색 실패!");
		}
	}

	// 유공자 birthday 미상일때 조회 하기
	public void searchByBirthday(String birthday) {
		try {
			EndView.projectListView(dmlService.searchByBirthday(birthday));
		} catch (SQLException e) {
			EndView.showError("유공자 생년월일 기반 검색 조회 실패!");
		}
	}

	// 유공자 lastday 미상일때 조회하기
	public void searchByLastday(String lastday) {
		try {
			EndView.projectListView(dmlService.searchByLastday(lastday));
		} catch (SQLException e) {
			EndView.showError("유공자 사망년월일 기반 조회 실패!");
		}
	}

	// 특정 관리번호 정보 삭제
	public void deleteMngNo(int mngNo) {
		try {
			dmlService.deleteMngNoAtBADDAO(mngNo);
			dmlService.deleteMngNo(mngNo);
			SuccessView.showSuccess("관리번호로 정보 삭제 성공!");
		} catch (SQLException e) {
			EndView.showError("관리번호로 정보 삭제 실패!");
		}
	}

	// 국가유공자 상세 조회
	public void searchInDetail(NationalMeritDTO nationalMeritDTO) {
		try {
			EndView.projectListView(dmlService.searchInDetail(nationalMeritDTO));
		} catch (SQLException e) {
			EndView.showError("유공자 상세조회 실패!!");
		}
	}

	// MultiTable
	// 특정 날짜에 생존한 사람 찾기
	public void findSurvivorsByDate(String date) {
		try {
			EndView.projectListView(dmlService.findSurvivorsByDate(date));
		} catch (SQLException e) {
			EndView.showError("당시 유공자 생애시기 찾기 실패!");
		}
	}

	// Birth And Death
	// 전체 조회하기
	public void searchAllBAD() {
		try {
			EndView.projectListView(dmlService.searchAllBAD());
		} catch (SQLException e) {
			EndView.showError("BAD 테이블 전체 조회 실패!");
		}
	}

	// 관리번호로 조회하기
	public void searchByMngnoBAD(int mngNo) {
		try {
			EndView.projectView(dmlService.searchByMngnoAtBADDAO(mngNo));
		} catch (SQLException e) {
			EndView.showError("일치하는 결과를 찾지 못했습니다.");
		}
	}

	// Reward 테이블
	// 추가 : 특정 훈장 조회 하기
	public void searchByMedal(String medal) {
		try {
			EndView.RewardView(dmlService.searchByReward(medal));
		} catch (SQLException e) {
			EndView.showError("훈장정보 조회 실패!");
		}
	}

	// 추가 : 훈장 전체 조회 하기
	public void searchByAllMedal() {
		try {
			EndView.projectListView(dmlService.searchByAllReward());
			SuccessView.showSuccess("모든 훈장 정보 조회 성공!");
		} catch (SQLException e) {
			EndView.showError("모든 훈장 정보 조회 실패!");
		}
	}

	// 추가 : 훈장 삭제
	public void deleteMedal(String medal) {
		try {
			dmlService.deleteMedal(medal);
			SuccessView.showSuccess("훈장 정보 삭제 성공!");
		} catch (SQLException e) {
			EndView.showError("훈장 정보 삭제 실패!");
		}
	}

	// 추가 : 훈장 추가
	public void InsertMedal(String medal, String explanation) {
		try {
			dmlService.InsertMedal(medal, explanation);
			SuccessView.showSuccess("훈장 추가 성공!");
		} catch (SQLException e) {
			EndView.showError("훈장 추가 실패!");
		}
	}

	// 추가 : 백업 관리번호조회
	public void searchByBackUp(int mngNo) {
		try {
			EndView.BackUpView(dmlService.searchByBackUp(mngNo));
		} catch (SQLException e) {
			EndView.showError("삭제된 정보 조회 실패");
		}
	}

	// 추가 : 백업 전체 조회 하기
	public void searchByAllBackUp() {
		try {
			EndView.projectListView(dmlService.searchByAllBackUp());
		} catch (SQLException e) {
			EndView.showError("모든 삭제된 정보 조회 실패!");
		}
	}

	// 스트링 값 입력을 위한 메소드
	public String scanString(String sentence) {
		Scanner sc = new Scanner(System.in);
		System.out.println(sentence);
		return sc.nextLine();
	}

	// 날짜값
	public String scanDate(String sentence) {
		Scanner scan = new Scanner(System.in);
		System.out.println(sentence);
		int length = 0;
		String num;

		while (true) {
			num = scan.nextLine();
			System.out.println(num);
			length = num.length();
			if (Pattern.matches("^[0-9]*$", num) && length == 8) {
				break;
			}
			System.out.println("유효하지 않은 값입니다. 생년월일 8자리를 입력해주세요.");
		}
		return String.valueOf(num);
	}

	// 숫자 필수
	public int scanInt(String sentence) {
		Scanner scan = new Scanner(System.in);
		System.out.println(sentence);

		while (!scan.hasNextInt()) {
			String str = scan.nextLine();
		}
		return scan.nextInt();
	}

	// 훈장 관련 입력값
	public String scanMedal(String sentence) {
		Scanner medal = new Scanner(System.in);
		System.out.println(sentence);
		String m = null;
		while (true) {
			m = medal.nextLine();
			if (m.equals("대한민국장") || m.equals("대통령장") || m.equals("독립장") || m.equals("애국장") || m.equals("애족장")
					|| m.equals("건국포장") || m.equals("대통령표창") || m.equals("서훈취소")) {
				break;
			} else {
				System.out.println("존재하지 않는 훈장 입니다.");
			}
		}
		return m;
	}

	// 훈장 관련 입력값
	public String scanSex(String sentence) {
		Scanner sex = new Scanner(System.in);
		System.out.println(sentence);
		String m = null;
		while (true) {
			m = sex.nextLine();
			if (m.equals("남") || m.equals("여")) {
				break;
			} else {
				System.out.println("존재하지 않는 성별 입니다.");
			}
		}
		return m;
	}
}
