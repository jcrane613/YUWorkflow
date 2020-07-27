package com.reljicd.model;

import org.springframework.stereotype.Component;

@Component
public class Settings {
	private boolean allowStudentReminders;
	private int daysBeforeReminder; 
	private String registrarEmail;
	private String accessibleWebsiteUrl;
	
	// Major Declaration Routing Table
	private String COM_routing;
	
	// Change of Torah Studies Routing Table
	
	// Leave Of Absence Routing Table

	public int getDaysBeforeReminder() {
		return daysBeforeReminder;
	}
	public void setDaysBeforeReminder(int daysBeforeReminder) {
		this.daysBeforeReminder = daysBeforeReminder;
	}
	public boolean isAllowStudentReminders() {
		return allowStudentReminders;
	}
	public void setAllowStudentReminders(boolean allowStudentReminders) {
		this.allowStudentReminders = allowStudentReminders;
	}
	public String getRegistrarEmail() {
		return registrarEmail;
	}
	public void setRegistrarEmail(String registrarEmail) {
		this.registrarEmail = registrarEmail;
	}
	public String getCOM_routing() {
		return COM_routing;
	}
	public void setCOM_routing(String cOM_routing) {
		COM_routing = cOM_routing;
	}
	public String getAccessibleWebsiteUrl() {
		return accessibleWebsiteUrl;
	}
	public void setAccessibleWebsiteUrl(String accessibleWebsiteUrl) {
		this.accessibleWebsiteUrl = accessibleWebsiteUrl;
	}
}
