package util;

public class CalenderUtil {

	public static CalenderUtil instance = new CalenderUtil();

	public static CalenderUtil getInstance() {
		return instance;
	}

	public int findLastDayInMonth(int year, int month) {

		int[] lastDay = new int[12];

		for (int i = 0; i < 12; i++) {
			// 2월인 경우
			if (i == (2 - 1)) {
				if (year % 4 == 0 && year % 100 == 0 && year % 400 == 0) {
					lastDay[i] = 29;
				} else if (year % 4 == 0 && year % 100 == 0) {
					lastDay[i] = 28;
				} else if (year % 4 == 0) {
					lastDay[i] = 29;
				} else {
					lastDay[i] = 28;
				}
			} else if (i == 1 - 1 || i == 3 - 1 || i == 5 - 1 || i == 7 - 1 || i == 8 - 1 || i == 10 - 1
					|| i == 12 - 1) {
				lastDay[i] = 31;
			} else if (i == 4 - 1 || i == 6 - 1 || i == 9 - 1 || i == 11 - 1) {
				lastDay[i] = 30;
			}
		}
		return lastDay[month - 1];
	}
}
