package model;

import java.sql.SQLException;

import data.BackUpDDL;
import data.BirthAndDeathDDL;
import data.NationalMeritDDL;
import data.RewardDDL;

public class DDLService {
	private static DDLService instance = new DDLService();
	private NationalMeritDDL nationalMeritDDL = NationalMeritDDL.getInstance();
	private BirthAndDeathDDL birthAndDeathDDL = BirthAndDeathDDL.getInstance();
	private RewardDDL rewardDDL = RewardDDL.getInstance();
	private BackUpDDL backUpDDL = BackUpDDL.getInstance();

	private DDLService() {
	}

	public static DDLService getInstance() {
		return instance;
	}

	public boolean createNMTable() throws SQLException {
		try {
			nationalMeritDDL.deleteTable();
			return nationalMeritDDL.createTable();
		} catch (java.sql.SQLSyntaxErrorException e) {
			return nationalMeritDDL.createTable();
		}
	}

	public boolean createBADTable() throws SQLException {
		try {
			birthAndDeathDDL.deleteTable();
			return birthAndDeathDDL.createTable();
		} catch (java.sql.SQLSyntaxErrorException e) {
			return birthAndDeathDDL.createTable();
		}
	}

	public boolean createRewardTable() throws SQLException {
		try {
			rewardDDL.deleteTable();
			rewardDDL.createTable();
			return rewardDDL.rewardInsert();
		} catch (java.sql.SQLSyntaxErrorException e) {
			rewardDDL.createTable();
			return rewardDDL.rewardInsert();
		}
	}

	public boolean createBackUpTable() throws SQLException {
		try {
			backUpDDL.dropBackUpTrigger();
			backUpDDL.deleteBackUpTable();
			backUpDDL.createBackUpTable();
			return backUpDDL.createBackUpTrigger();
		} catch (Exception e) {
			try {
				backUpDDL.deleteBackUpTable();
				backUpDDL.createBackUpTable();
				return backUpDDL.createBackUpTrigger();
			} catch (java.sql.SQLSyntaxErrorException e1) {
				backUpDDL.createBackUpTable();
				return backUpDDL.createBackUpTrigger();
			}
		}
		
		
	}
}
