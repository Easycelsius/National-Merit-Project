package data;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import dto.NationalMeritDTO;

public class DataCollector {

	private DataCollector() {}
	
	// 싱글톤
	public static DataCollector instance = new DataCollector();
	
	public static DataCollector getInstance() {
		return instance;
	}
	
	public ArrayList<NationalMeritDTO> startCrwaling() {
		
		int page = 1;
		
		// SSL 셋팅
		System.setProperty("jsse.enableSNIExtension", "false");
		
		int totalCount = 0;
		ArrayList<NationalMeritDTO> arr = new ArrayList<>();

		do {

			String url = "https://e-gonghun.mpva.go.kr/opnAPI/publicReportList.do?nPageIndex=" + page + "&nCountPerPage=50";
			Document doc = null;

			try {
				doc = Jsoup.connect(url).get();
				totalCount = Integer.parseInt(doc.select("TOTAL_COUNT").text());
				Elements mngNo = doc.select("MNG_NO");
				Elements nameKo = doc.select("NAME_KO");
				Elements nameCh = doc.select("NAME_CH");
				Elements diffName = doc.select("DIFF_NAME");
				Elements birthday = doc.select("BIRTHDAY");
				Elements lastDay = doc.select("LASTDAY");
				Elements sex = doc.select("SEX");
				Elements registerLargeDiv = doc.select("REGISTER_LARGE_DIV");
				Elements registerMidDiv = doc.select("REGISTER_MID_DIV");
				Elements registerSmallDiv = doc.select("REGISTER_SMALL_DIV");
				Elements judgeYear = doc.select("JUDGE_YEAR");
				Elements hunkuk = doc.select("HUNKUK");
				Elements workoutAffil = doc.select("WORKOUT_AFFIL");
				Elements achivementKo = doc.select("ACHIVEMENT_KO");

				for (int i = 0; i < mngNo.size(); i++) {
					arr.add(new NationalMeritDTO(Integer.parseInt((mngNo.get(i)).text()), nameKo.get(i).text(),
							nameCh.get(i).text(), diffName.get(i).text(), birthday.get(i).text(), lastDay.get(i).text(),
							sex.get(i).text(), registerLargeDiv.get(i).text(), registerMidDiv.get(i).text(),
							registerSmallDiv.get(i).text(), judgeYear.get(i).text(), hunkuk.get(i).text(),
							workoutAffil.get(i).text(), achivementKo.get(i).text()));
				}
				
				System.out.println(page * 50);
				
				page += 1;
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		} while (page * 50 <= totalCount);

		return arr;
	}

}
