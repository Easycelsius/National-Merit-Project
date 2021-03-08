package dto;

public class BirthAndDeathDTO {
	public BirthAndDeathDTO(int mngNo, String proBirthday, String proLastday) {
		super();
		this.mngNo = mngNo;
		this.proBirthday = proBirthday;
		this.proLastday = proLastday;
	}
	private int mngNo;
	private String proBirthday;
	private String proLastday;
	public int getMngNo() {
		return mngNo;
	}
	public void setMngNo(int mngNo) {
		this.mngNo = mngNo;
	}
	public String getProBirthday() {
		return proBirthday;
	}
	public void setProBirthday(String proBirthday) {
		this.proBirthday = proBirthday;
	}
	public String getProLastday() {
		return proLastday;
	}
	public void setProLastday(String proLastday) {
		this.proLastday = proLastday;
	}
	@Override
	public String toString() {
		return "BirthAndDeathDTO [mngNo=" + mngNo + ", proBirthday=" + proBirthday + ", proLastday=" + proLastday + "]";
	}
}
