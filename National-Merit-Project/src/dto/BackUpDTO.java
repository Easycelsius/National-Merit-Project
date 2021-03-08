package dto;

public class BackUpDTO extends NationalMeritDTO{
	
	private NationalMeritDTO nationalMeritDTO;
	private String deleteDate;
	
	public BackUpDTO() {}
	
	public BackUpDTO(int mngNo, String nameKo, String nameCh, String diffName, String birthday, String lastday,
			String sex, String registerLargeDiv, String registerMidDiv, String registerSmallDiv, String judgeYear,
			String hunkuk, String workoutAffil, String achivementKo, String date) {
		super(mngNo, nameKo, nameCh, diffName, birthday, lastday, sex, registerLargeDiv, registerMidDiv, registerSmallDiv, judgeYear, hunkuk, workoutAffil, achivementKo);
		this.deleteDate = date;
	}
	
	public BackUpDTO(NationalMeritDTO nationalMeritDTO, String date) {
		super();
		this.nationalMeritDTO = nationalMeritDTO;
		this.deleteDate = date;
	}
	
	public NationalMeritDTO getNationalMeritDTO() {
		return nationalMeritDTO;
	}
	public void setNationalMeritDTO(NationalMeritDTO nationalMeritDTO) {
		this.nationalMeritDTO = nationalMeritDTO;
	}
	public String getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(", deleteDate : ");
		builder.append(deleteDate);
		builder.append("]");
		return builder.toString();
	}
	
	
}
