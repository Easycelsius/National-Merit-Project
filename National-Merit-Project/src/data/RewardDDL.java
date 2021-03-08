package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DButil;

public class RewardDDL {
	public static RewardDDL instance = new RewardDDL();
	
	public static RewardDDL getInstance() {
		return instance;
	}
	
	//medal : 데이터 크기 : 18;
	//explanation : 데이터 크기 3000
	public boolean createTable() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DButil.getConnection();

			pstmt = con.prepareStatement("CREATE TABLE reward("
					+"MEDAL VARCHAR2(18) PRIMARY KEY,"
					+"EXPLANATION VARCHAR2(3000) NOT NULL"
					+ ")");
			pstmt.execute();
			
		}finally {
			DButil.close(con, pstmt);
		}
		return true;
	}
	
	public boolean deleteTable() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("DROP TABLE reward CASCADE CONSTRAINT");
			pstmt.execute();
		}finally {
			DButil.close(con, pstmt);
		}
		return true;
	}
	
	//데이터 저장 처럼 해주면된다.
	public boolean rewardInsert() {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DButil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO reward VALUES('대한민국장','대한민국의 건국에 공로가 뚜렷하거나, 국가의 기초를 공고히 하는 데에 이바지한 공적이 뚜렷한 사람에게 수여하는 대한민국의 건국훈장 중 최고등급')");
			pstmt.execute();
			pstmt.close();
			
			pstmt = con.prepareStatement("INSERT INTO reward VALUES('대통령장','대한민국의 건국에 공로가 뚜렷하거나, 국가의 기초를 공고히 하는 데에 이바지한 공적이 뚜렷한 사람에게 수여하는 대한민국의 건국훈장 중 두번째 등급이다')");
			pstmt.execute();
			pstmt.close();
			
			pstmt = con.prepareStatement("INSERT INTO reward VALUES('독립장','대한민국의 건국에 공로가 뚜렷하거나, 국가의 기초를 공고히 하는 데에 이바지한 공적이 뚜렷한 사람에게 수여하는 대한민국의 건국훈장 중 세번째 등급이다')");
			pstmt.execute();
			pstmt.close();
			
			pstmt = con.prepareStatement("INSERT INTO reward VALUES('애국장','대한민국의 건국에 공로가 뚜렷하거나, 국가의 기초를 공고히 하는 데에 이바지한 공적이 뚜렷한 사람에게 수여하는 대한민국의 건국훈장 중 네번째 등급이다')");
			pstmt.execute();
			pstmt.close();
			
			pstmt = con.prepareStatement("INSERT INTO reward VALUES('애족장','대한민국의 건국에 공로가 뚜렷하거나, 국가의 기초를 공고히 하는 데에 이바지한 공적이 뚜렷한 사람에게 수여하는 대한민국의 건국훈장 중 다섯번째 등급이다')");
			pstmt.execute();
			pstmt.close();
			
			pstmt = con.prepareStatement("INSERT INTO reward VALUES('건국포장','건국포장은 대한민국의 건국과 국기를 공고하는 데 헌신 진력하여 그 공적이 뚜렷한 자에게 수여한다.')");
			pstmt.execute();
			pstmt.close();
			
			pstmt = con.prepareStatement("INSERT INTO reward VALUES('대통령표창','대통령표창은 대한민국에 공적(功績)을 세우거나 각종 교육·경기 및 경연 등에서 우수한 성적을 거둔 자에게 수여한다')");
			pstmt.execute();
			pstmt.close();
			
			pstmt = con.prepareStatement("INSERT INTO reward VALUES('서훈취소','서훈공적이 거짓임이 판명된 경우’에 서훈 수여 당시 드러나지 않은 사실이 새로 밝혀졌고 그 사실이 서훈 심사 당시 밝혀졌더라면 당초 조사된 공적사실과 새로 밝혀진 사실을 전체적으로 평가했을 때 서훈대상자의 행적을 서훈에 관한 공적으로 인정할 수 없는 경우이다')");
			pstmt.execute();
			pstmt.close();
			
			// 제약식 추가
			pstmt = con.prepareStatement("ALTER TABLE nationalmerit ADD CONSTRAINT NATIONALMERIT_HUNKUK_FK FOREIGN KEY(HUNKUK) REFERENCES REWARD(MEDAL)");
			pstmt.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
