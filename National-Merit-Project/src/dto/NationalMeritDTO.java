package dto;

public class NationalMeritDTO {
	private int mngNo;
	private String nameKo;
	private String nameCh;
	private String diffName;
	private String birthday;
	private String lastday;
	private String sex;
	private String registerLargeDiv;
	private String registerMidDiv;
	private String registerSmallDiv;
	private String judgeYear;
	private String hunkuk;
	private String workoutAffil;
	private String achivementKo;
	
	public NationalMeritDTO() {}
	public NationalMeritDTO(int mngNo, String nameKo, String nameCh, String diffName, String birthday, String lastday,
			String sex, String registerLargeDiv, String registerMidDiv, String registerSmallDiv, String judgeYear,
			String hunkuk, String workoutAffil, String achivementKo) {
		super();
		this.mngNo = mngNo;
		this.nameKo = nameKo;
		this.nameCh = nameCh;
		this.diffName = diffName;
		this.birthday = birthday;
		this.lastday = lastday;
		this.sex = sex;
		this.registerLargeDiv = registerLargeDiv;
		this.registerMidDiv = registerMidDiv;
		this.registerSmallDiv = registerSmallDiv;
		this.judgeYear = judgeYear;
		this.hunkuk = hunkuk;
		this.workoutAffil = workoutAffil;
		this.achivementKo = achivementKo;
	}
	
	public int getMngNo() {
		return mngNo;
	}
	public void setMngNo(int mngNo) {
		this.mngNo = mngNo;
	}
	public String getNameKo() {
		return nameKo;
	}
	public void setNameKo(String nameKo) {
		this.nameKo = nameKo;
	}
	public String getNameCh() {
		return nameCh;
	}
	public void setNameCh(String nameCh) {
		this.nameCh = nameCh;
	}
	public String getDiffName() {
		return diffName;
	}
	public void setDiffName(String diffName) {
		this.diffName = diffName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getLastday() {
		return lastday;
	}
	public void setLastday(String lastday) {
		this.lastday = lastday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRegisterLargeDiv() {
		return registerLargeDiv;
	}
	public void setRegisterLargeDiv(String registerLargeDiv) {
		this.registerLargeDiv = registerLargeDiv;
	}
	public String getRegisterMidDiv() {
		return registerMidDiv;
	}
	public void setRegisterMidDiv(String registerMidDiv) {
		this.registerMidDiv = registerMidDiv;
	}
	public String getRegisterSmallDiv() {
		return registerSmallDiv;
	}
	public void setRegisterSmallDiv(String registerSmallDiv) {
		this.registerSmallDiv = registerSmallDiv;
	}
	public String getJudgeYear() {
		return judgeYear;
	}
	public void setJudgeYear(String iudgeYear) {
		this.judgeYear = iudgeYear;
	}
	public String getHunkuk() {
		return hunkuk;
	}
	public void setHunkuk(String hunkuk) {
		this.hunkuk = hunkuk;
	}
	public String getWorkoutAffil() {
		return workoutAffil;
	}
	public void setWorkoutAffil(String workoutAffil) {
		this.workoutAffil = workoutAffil;
	}
	public String getAchivementKo() {
		return achivementKo;
	}
	public void setAchivementKo(String achivementKo) {
		this.achivementKo = achivementKo;
	}
	
	@Override
	public String toString() {
		return "NationalMeritDTO [mngNo=" + mngNo + ", nameKo=" + nameKo + ", nameCh=" + nameCh + ", diffName="
				+ diffName + ", birthday=" + birthday + ", lastday=" + lastday + ", sex=" + sex + ", registerLargeDiv="
				+ registerLargeDiv + ", registerMidDiv=" + registerMidDiv + ", registerSmallDiv=" + registerSmallDiv
				+ ", judgeYear=" + judgeYear + ", hunkuk=" + hunkuk + ", workoutAffil=" + workoutAffil
				+ ", achivementKo=" + achivementKo + "]";
	}
}
