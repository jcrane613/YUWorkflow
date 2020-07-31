package com.reljicd.config;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class GlobalSettings {
	public boolean studentRemindersAllowed = false;
	public int daysBeforeReminder = 7;
	public String registrarEmail = "yaircaplan@gmail.com";
	public String accessibleWebsiteUrl = "http://localhost:8070/";
	
	// Routing Tables
	public Map<String , String> majorToApproverMap = new HashMap<>();
	public Map<String , String> torahStudiesToApproverMap = new HashMap<>();

	// Leave Of Absence Settings
	//public String leaveOfAbApprover1 = "approver1";
	public String leaveOfAbApprover2 = "approver2";
	public String leaveOfAbApprover3 = "approver1";
	public Map<String , String> schoolToDeanMap = new HashMap<>();
	public String registrar = "approver1";


	
	

	public GlobalSettings() {
		populateMapsWithDefaults();
	}
	
	private void populateMapsWithDefaults(){
		majorToApproverMap.put("COM" , "approver1");
		majorToApproverMap.put("ART" , "approver2");
		majorToApproverMap.put("MAT" , "approver3");
		majorToApproverMap.put("JST" , "approver4");
		majorToApproverMap.put("HIS" , "approver5");
		majorToApproverMap.put("LAW" , "approver6");
		majorToApproverMap.put("GEM" , "approver7");
		
		torahStudiesToApproverMap.put("IBC" , "approver1");
		torahStudiesToApproverMap.put("Mechinah/JSS" , "approver2");
		torahStudiesToApproverMap.put("MYP" , "approver3");
		torahStudiesToApproverMap.put("SBMP" , "approver4");
		
		schoolToDeanMap.put("KATZ", "approver1");
		schoolToDeanMap.put("RIETS", "approver2");
	}
	
	
	public void setRegistrarEmail(String registrarEmail) {
		this.registrarEmail = registrarEmail;
	}
	public void setDaysBeforeReminder(int daysBeforeReminder) {
		this.daysBeforeReminder = daysBeforeReminder;
	}
	public void setAllowStudentReminders(boolean allowStudentReminders) {
		this.studentRemindersAllowed = allowStudentReminders;
	}
	public void setAccessibleWebsiteUrl(String accessibleWebsiteUrl) {
		this.accessibleWebsiteUrl = accessibleWebsiteUrl;
	}
	
	// Leave Of Absence Setters
	public void setRegistrar(String registrar) {
		this.registrar = registrar;
	}
	public void setLeaveOfAbApprover2(String leaveOfAbApprover2) {
		this.leaveOfAbApprover2 = leaveOfAbApprover2;
	}
	public void setLeaveOfAbApprover3(String leaveOfAbApprover3) {
		this.leaveOfAbApprover3 = leaveOfAbApprover3;
	}
}
