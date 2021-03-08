package view;

import java.sql.SQLException;
import controller.Controller;
import dto.NationalMeritDTO;

public class StartView {

	// 추후 반환 필요

	public static void main(String[] args) throws SQLException, InterruptedException {
		Controller controller = Controller.getInstance();

		while (true) {
			System.out.println();
			System.out.println("==== 국가 유공자 검색 서비스에 오신 것을 환영합니다. ====");

			String sc1 = controller.scanString("접속시 권한을 입력해주세요. (종료:exit)");

			if (sc1.equals("admin")) {
				while (true) {
					System.out.println();
					System.out.println("==== 관리자 페이지에 오신 것을 환영합니다 ====");
					System.out.println("-------------------");
					System.out.println("01. 명령 프롬프트 열기");
					System.out.println("02. DB 테이블 생성하기");
					System.out.println("03. 데이터 크롤링 진행하기");
					System.out.println("-------------------");
					System.out.println("04. 국가유공자 테이블 접근");
					System.out.println("05. 백업 테이블 접근");
					System.out.println("06. 가공 생년월일 테이블 접근");
					System.out.println("07. 국가 훈장 테이블 접근");
					System.out.println("-------------------");
					System.out.println("99. 되돌아가기");
					System.out.println("-------------------");

					Thread.sleep(1000);
					int adminScan1 = controller.scanInt("원하시는 서비스의 번호를 입력해주세요");

					if (adminScan1 == 1) {

						System.out.println("명령프롬프트 실행");
						util.CmdUtil.cmdStart();
						while (util.CmdUtil.cmdFlag) {
							Thread.activeCount();
						}
						util.CmdUtil.cmdFlag = true;
						Thread.interrupted();

					} else if (adminScan1 == 2) {
						controller.createTables();
					} else if (adminScan1 == 3) {
						controller.initInsertData();
					} else if (adminScan1 == 4) {
						while (true) {
							System.out.println();
							System.out.println("==== 국가유공자 테이블 관리 페이지 ====");
							System.out.println("01. 국가유공자 테이블 전체 조회");
							System.out.println("02. 국가유공자 테이블 관리번호로 조회");
							System.out.println("03. 국가유공자 테이블에 유공자 추가");
							System.out.println("04. 국가유공자 테이블 관리번호로 삭제");
							System.out.println("99. 되돌아가기");

							int adminScan14 = controller.scanInt("원하시는 서비스의 번호를 입력해주세요");

							if (adminScan14 == 1) {
								controller.searchAll();
							} else if (adminScan14 == 2) {
								controller.searchByMngno(controller.scanInt("조회할 관리 번호를 입력해주세요"));
							} else if (adminScan14 == 4) {
								controller.deleteMngNo(controller.scanInt("조회할 관리 번호를 입력해주세요"));
							} else if (adminScan14 == 3) {
								System.out.println("만약에 정보가 없다면 null로 입력해주세요.");
								controller.insertData(
										new NationalMeritDTO(controller.scanInt("관리 번호를 입력해주세요"), controller.scanString("한글 이름을 입력해주세요."),
												controller.scanString("한자 이름을 입력해주세요."), controller.scanString("이명을 입력해주세요."),
												controller.scanString("생년월일을 입력해주세요."), controller.scanString("사망년월일을 입력해주세요."),
												controller.scanSex("성별을 입력해주세요. (남/여)"), controller.scanString("본적 대분류를 입력해주세요."),
												controller.scanString("본적 중분류를 입력해주세요."), controller.scanString("본적 소분류를 입력해주세요."),
												controller.scanString("포상년도를 입력해주세요."), controller.scanString("훈격을 입력해주세요."),
												controller.scanString("운동계열을 입력해주세요."), controller.scanString("공적개요 (국한문 병기 사항)을 입력해주세요")));
							} else if (adminScan14 == 99) {
								break;
							}
						}
					} else if (adminScan1 == 5) {
						while (true) {
							System.out.println();
							System.out.println("==== 백업 테이블 관리 페이지 ====");
							System.out.println("01. 국가유공자 테이블 삭제로그 확인하기"); // O
							System.out.println("99. 되돌아가기"); // O

							int adminScan15 = controller.scanInt("원하시는 서비스의 번호를 입력해주세요");

							if (adminScan15 == 1) {
								controller.searchByAllBackUp();
							} else if (adminScan15 == 99) {
								break;
							}
						}
					} else if (adminScan1 == 6) {
						while (true) {
							System.out.println();
							System.out.println("==== 가공 생년월일 테이블 관리 페이지 ====");
							System.out.println("01. 가공 생년월일 전체 조회하기"); // O
							System.out.println("02. 가공 생년월일 관리번호로 조회하기"); // O
							System.out.println("99. 되돌아가기");

							int adminScan16 = controller.scanInt("원하시는 서비스의 번호를 입력해주세요");

							if (adminScan16 == 1) {
								controller.searchAllBAD();
							} else if (adminScan16 == 2) {
								controller.searchByMngnoBAD(controller.scanInt("관리 번호를 입력해주세요"));
							} else if (adminScan16 == 99) {
								break;
							}
						}
					} else if (adminScan1 == 7) {
						while (true) {
							System.out.println();
							System.out.println("==== 국가 훈장 테이블 관리 페이지 ====");
							System.out.println("01. 국가 훈장 정보 전체 조회");
							System.out.println("02. 국가 훈장 정보 검색으로 조회");
							System.out.println("03. 국가 훈장 정보 추가");
							System.out.println("04. 국가 훈장 정보 삭제");
							System.out.println("99. 되돌아가기");

							int adminScan17 = controller.scanInt("원하시는 서비스의 번호를 입력해주세요");

							if (adminScan17 == 1) {
								controller.searchByAllMedal();
							} else if (adminScan17 == 2) {
								controller.searchByMedal(
										controller.scanMedal("검색할 훈장을 입력하세요.(대한민국장, 대통령장, 독립장, 애국장, 애족장, 건국포장, 대통령표창, 서훈취소)"));
							} else if (adminScan17 == 3) {
								controller.InsertMedal(controller.scanMedal("추가할 훈장을 입력하세요."), controller.scanString("추가할 훈장에 대한 설명을 입력하세요"));
							} else if (adminScan17 == 4) {
								controller.deleteMedal(controller.scanMedal("삭제할 훈장을 입력하세요"));
							} else if (adminScan17 == 99) {
								break;
							}
						}
					} else if (adminScan1 == 99) {
						break;
					}
				}

			} else if (sc1.equals("user")) {
				while (true) {
					System.out.println();
					System.out.println("==== 사용자 페이지에 오신 것을 환영합니다 ====");
					System.out.println("-------------------");
					System.out.println("01. 국가유공자 기능별 검색");
					System.out.println("02. 국가유공자 다중/상세 검색");
					System.out.println("-------------------");
					System.out.println("03. 국가 훈장 정보 검색");
					System.out.println("-------------------");
					System.out.println("99. 되돌아가기");

					int userScan2 = controller.scanInt("원하시는 서비스의 번호를 입력해주세요");

					if (userScan2 == 1) {
						while (true) {
							System.out.println();
							System.out.println("==== 국가 유공자 기능별 검색 페이지 ====");
							System.out.println("01. 국가 유공자 성명으로 검색");
							System.out.println("02. 국가 유공자 한자명으로 검색");
							System.out.println("03. 국가 유공자 이명으로 검색");
							System.out.println("04. 국가 유공자 생년월일 검색");
							System.out.println("05. 국가 유공자 사망년월일로 검색");
							System.out.println("06. 국가 유공자 성별로 조회");
							System.out.println("07. 국가 유공자 본적대분류로 조회");
							System.out.println("08. 국가 유공자 본적중분류로 조회");
							System.out.println("09. 국가 유공자 본적소분류로 조회");
							System.out.println("10. 국가 유공자 포상년도로 조회");
							System.out.println("11. 국가 유공자 훈격으로 조회");
							System.out.println("12. 국가 유공자 운동계열로 조회");
							System.out.println("13. 국가 유공자 공적개요(국한문 병기) 검색");
							System.out.println("99. 되돌아가기");

							int userScan21 = controller.scanInt("원하시는 서비스의 번호를 입력해주세요");

							if (userScan21 == 1) {
								controller.searchByNameKo(controller.scanString("조회하실 성명을 한글로 입력해 주세요."));
								// sample : 가네코 후미코
							} else if (userScan21 == 2) {
								controller.searchByNameCh(controller.scanString("조회하실 성명을 한자로 입력해 주세요."));
								// sample : 金子文子
							} else if (userScan21 == 3) {
								controller.searchByDiffName(controller.scanString("국가 유공자의 이명을 입력해 주세요."));
								// sample : 甘益龍
							} else if (userScan21 == 4) {
								controller.searchByBirthday(controller.scanString("국가 유공자의 생년월일과 관련하여 입력해 주세요."));
								// sample : 1895
							} else if (userScan21 == 5) {
								controller.searchByLastday(controller.scanString("국가 유공자의 사망년월일과 관련하여 입력해 주세요."));
								// sample : 미상
							} else if (userScan21 == 6) {
								controller.searchBySex(controller.scanSex("국가 유공자의 성별을 입력해 주세요. (남/여)"));
								// 여, 남
							} else if (userScan21 == 7) {
								controller.searchByRegisterLargeDiv(
										controller.scanString("국가 유공자의 본적대분류를 입력해 주세요. (외국, 함경북도, 경상남도 등)"));
								// 외국, 충청남도, 경상남도, 함경북도 등등
							} else if (userScan21 == 8) {
								controller.searchByRegisterMidDiv(controller.scanString("국가 유공자의 본적중분류를 입력해 주세요. (안동, 문경 등)"));
								// 도시이름
							} else if (userScan21 == 9) {
								controller.searchByRegisterSmallDiv(controller.scanString("국가 유공자의 본적소분류를 입력해 주세요. (삼화 등)"));
								// 세부지역명
							} else if (userScan21 == 10) {
								controller.searchByJudgeYear(controller.scanString("국가 유공자의 포상년도를 입력해 주세요."));
								// 포상년도 입력
							} else if (userScan21 == 11) {
								controller.searchByHunkuk(
										controller.scanMedal("검색할 훈장을 입력하세요.(대한민국장, 대통령장, 독립장, 애국장, 애족장, 건국포장, 대통령표창, 서훈취소)"));
								// 훈격 정보
							} else if (userScan21 == 12) {
								controller.searchByWorkoutAffil(controller.scanString(
										"검색할 운동을 입력하세요.\n(의병, 3.1운동, 문화운동, 국내항일, 의열투쟁, 학생운동, 광복군,\n계몽운동, 임시정부, 일본방면, 만주방면, 중국방면, 노령방면, 미주방면, 미정, 인도네시아방면,\n3.1운동지원, 광복군지원, 임시정부지원, 계몽운동지원, 의열투쟁지원, 독립운동지원, 구주방면)"));
								// 운동계열 입력
							} else if (userScan21 == 13) {
								controller.searchByAchivementKo(controller.scanString("국가 유공자 공적개요와 관련하여 검색어를 입력해 주세요."));
							} else if (userScan21 == 99) {
								break;
							}
						}
					} else if (userScan2 == 2) {
						while (true) {
							System.out.println();
							System.out.println("==== 국가유공자 다중/상세 검색 ====");
							System.out.println("01. 국가유공자 다중검색 조회");
							System.out.println("02. 국가유공자 생애시기별 조회");
							System.out.println("99. 되돌아가기");

							int userScan22 = controller.scanInt("원하시는 서비스의 번호를 입력해주세요");

							if (userScan22 == 2) {
								controller.findSurvivorsByDate(controller.scanDate("연월일을 입력해주세요.(예시: 18600101)"));
							} else if (userScan22 == 1) {
								System.out.println("해당 부분을 검색하지 않으시려면 \"N/A\"를 입력해주세요.");
								controller.searchInDetail(new NationalMeritDTO(0000, controller.scanString("한글 이름을 입력해주세요."),
										controller.scanString("한자 이름을 입력해주세요."), controller.scanString("이명을 입력해주세요."),
										controller.scanString("생년월일을 입력해주세요."), controller.scanString("사망년월일을 입력해주세요."),
										controller.scanSex("성별을 입력해주세요. (남/여)"), controller.scanString("본적 대분류를 입력해주세요."),
										controller.scanString("본적 중분류를 입력해주세요."), controller.scanString("본적 소분류를 입력해주세요."),
										controller.scanString("포상년도를 입력해주세요."),
										controller.scanString("검색할 훈장을 입력하세요.(대한민국장, 대통령장, 독립장, 애국장, 애족장, 건국포장, 대통령표창, 서훈취소)"),
										controller.scanString("운동계열을 입력해주세요."), controller.scanString("공적개요 (국한문 병기 사항)을 입력해주세요")));
							} else if (userScan22 == 99) {
								break;
							}
						}

					} else if (userScan2 == 3) {
						while (true) {
							System.out.println();
							System.out.println("==== 국가 훈장 정보 검색 ====");
							System.out.println("01. 국가 훈장 정보 전체 조회");
							System.out.println("02. 국가 훈장 개별 정보 조회");
							System.out.println("99. 되돌아가기");

							int userScan23 = controller.scanInt("원하시는 서비스의 번호를 입력해주세요");

							if (userScan23 == 1) {
								// 훈장 정보 전체
								controller.searchByAllMedal();
							} else if (userScan23 == 2) {
								// 훈장 개별 정보 조회
								controller.searchByHunkuk(
										controller.scanMedal("검색할 훈장을 입력하세요.(대한민국장, 대통령장, 독립장, 애국장, 애족장, 건국포장, 대통령표창, 서훈취소)"));
							} else if (userScan23 == 99) {
								break;
							}
						}

					} else if (userScan2 == 99) {
						break;
					}
				}
			} else if (sc1.equals("exit") || sc1.equals("99")) {
				System.out.println("종료되었습니다.");
				break;
			}
		}
		System.exit(0);
	}
}
