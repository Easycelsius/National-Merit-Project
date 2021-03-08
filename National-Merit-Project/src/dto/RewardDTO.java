package dto;

public class RewardDTO {
	
	private String medal;       //데이터 크기 18
	private String explanation; //데이터 크기 3000
	
	public RewardDTO(){}
	
	public RewardDTO(String medal, String explanation) {
		super();
		this.medal = medal;
		this.explanation = explanation;
	}
	
	public String getMedal() {
		return medal;
	}
	public void setMedal(String medal) {
		this.medal = medal;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	@Override
	public String toString() {
		return "rewardDTO [medal=" + medal + ", explanation=" + explanation + "]";
	}
}
