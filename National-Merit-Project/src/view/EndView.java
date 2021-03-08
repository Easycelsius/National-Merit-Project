package view;

import java.util.ArrayList;

import dto.NationalMeritDTO;
import dto.RewardDTO;
import dto.BackUpDTO;
import dto.BirthAndDeathDTO;

public class EndView {

	// 모든 프로젝트 출력
	public static void projectListView(ArrayList<?> allProject) {
		int length = allProject.size();
		if (length != 0) {
			for (int index = 0; index < length; index++) {
				System.out.println("검색정보 " + (index + 1) + " - " + allProject.get(index));
			}
		} else if (length == 0) {
			System.out.println("조회가 정상적으로 수행되었으나, 데이터가 없습니다.");
		}
	}

	// 특정 프로젝트 출력
	public static void projectView(NationalMeritDTO project) {
		if(project == null) {
			System.out.println("요청하신 결과가 없습니다.");
		} else {
			System.out.println(project);			
		}
	}

	// 특정 프로젝트 출력
	public static void projectView(BirthAndDeathDTO project) {
		if(project == null) {
			System.out.println("요청하신 결과가 없습니다.");
		} else {
			System.out.println(project);			
		}
	}

	// 모든 DTO 정보 출력하는 메소드
	public static void allView(Object object) {
		System.out.println(object);
	}

	// 예외 상황 출력
	public static void showError(String message) {
		System.out.println(message);
	}

	// 특정 훈장 출력
	public static void RewardView(RewardDTO medal) {
		System.out.println(medal);
	}

	// 특정 BackUp 출력
	public static void BackUpView(BackUpDTO mngNo) {
		System.out.println(mngNo);
	}
}
