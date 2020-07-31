package workflow.model;

import workflow.config.GlobalSettings;


public class Settings {
	private boolean studentRemindersAllowed;
	private int daysBeforeReminder; 
	private String registrarEmail;
	private String accessibleWebsiteUrl;
	
	// Major Declaration Routing Table
	private String com_dean;
	private String art_dean;
	private String mat_dean;
	private String jst_dean;
	private String his_dean;
	private String law_dean;
	private String gem_dean;
	
	// Change of Torah Studies Routing Table
	private String ibc_dean;
	private String jss_dean;
	private String myp_dean;
	private String sbmp_dean;
	
	// Leave Of Absence Routing Table
	private String katz_dean;
	private String riets_dean;


	public Settings() {
	}
	
	public Settings(GlobalSettings globalSettings) {
		this.studentRemindersAllowed = globalSettings.studentRemindersAllowed;
		this.daysBeforeReminder = globalSettings.daysBeforeReminder;
		this.registrarEmail = globalSettings.registrarEmail;
		this.accessibleWebsiteUrl = globalSettings.accessibleWebsiteUrl;
		
		this.com_dean = globalSettings.majorToApproverMap.get("COM");
		this.art_dean = globalSettings.majorToApproverMap.get("ART");
		this.mat_dean = globalSettings.majorToApproverMap.get("MAT");
		this.jst_dean = globalSettings.majorToApproverMap.get("JST");
		this.his_dean = globalSettings.majorToApproverMap.get("HIS");
		this.law_dean = globalSettings.majorToApproverMap.get("LAW");
		this.gem_dean = globalSettings.majorToApproverMap.get("GEM");
	
		this.ibc_dean = globalSettings.torahStudiesToApproverMap.get("IBC");
		this.jss_dean = globalSettings.torahStudiesToApproverMap.get("Mechinah/JSS");
		this.myp_dean = globalSettings.torahStudiesToApproverMap.get("MYP");
		this.sbmp_dean = globalSettings.torahStudiesToApproverMap.get("SBMP");

		this.katz_dean = globalSettings.schoolToDeanMap.get("KATZ");
		this.riets_dean = globalSettings.schoolToDeanMap.get("RIETS");

	}

	public int getDaysBeforeReminder() {
		return daysBeforeReminder;
	}
	public void setDaysBeforeReminder(int daysBeforeReminder) {
		this.daysBeforeReminder = daysBeforeReminder;
	}
	public boolean isAllowStudentReminders() {
		return studentRemindersAllowed;
	}
	public void setAllowStudentReminders(boolean allowStudentReminders) {
		this.studentRemindersAllowed = allowStudentReminders;
	}
	public String getRegistrarEmail() {
		return registrarEmail;
	}
	public void setRegistrarEmail(String registrarEmail) {
		this.registrarEmail = registrarEmail;
	}
	public String getAccessibleWebsiteUrl() {
		return accessibleWebsiteUrl;
	}
	public void setAccessibleWebsiteUrl(String accessibleWebsiteUrl) {
		this.accessibleWebsiteUrl = accessibleWebsiteUrl;
	}
	public String getCom_dean() {
		return com_dean;
	}
	public void setCom_dean(String com_dean) {
		this.com_dean = com_dean;
	}
	public String getArt_dean() {
		return art_dean;
	}
	public void setArt_dean(String art_dean) {
		this.art_dean = art_dean;
	}
	public String getMat_dean() {
		return mat_dean;
	}
	public void setMat_dean(String mat_dean) {
		this.mat_dean = mat_dean;
	}
	public String getJst_dean() {
		return jst_dean;
	}
	public void setJst_dean(String jst_dean) {
		this.jst_dean = jst_dean;
	}
	public String getHis_dean() {
		return his_dean;
	}
	public void setHis_dean(String his_dean) {
		this.his_dean = his_dean;
	}
	public String getLaw_dean() {
		return law_dean;
	}
	public void setLaw_dean(String law_dean) {
		this.law_dean = law_dean;
	}
	public String getGem_dean() {
		return gem_dean;
	}
	public void setGem_dean(String gem_dean) {
		this.gem_dean = gem_dean;
	}
	public String getIbc_dean() {
		return ibc_dean;
	}
	public void setIbc_dean(String ibc_dean) {
		this.ibc_dean = ibc_dean;
	}
	public String getJss_dean() {
		return jss_dean;
	}
	public void setJss_dean(String jss_dean) {
		this.jss_dean = jss_dean;
	}
	public String getMyp_dean() {
		return myp_dean;
	}
	public void setMyp_dean(String myp_dean) {
		this.myp_dean = myp_dean;
	}
	public String getSbmp_dean() {
		return sbmp_dean;
	}
	public void setSbmp_dean(String sbmp_dean) {
		this.sbmp_dean = sbmp_dean;
	}
	public String getKatz_dean() {
		return katz_dean;
	}
	public void setKatz_dean(String katz_dean) {
		this.katz_dean = katz_dean;
	}
	public String getRiets_dean() {
		return riets_dean;
	}
	public void setRiets_dean(String riets_dean) {
		this.riets_dean = riets_dean;
	}
	
}
