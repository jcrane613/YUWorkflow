package com.redteam.model;

import org.springframework.stereotype.Component;

@Component
public class Settings {
	private boolean allowStudentReminders = false;

	public boolean isAllowStudentReminders() {
		return allowStudentReminders;
	}
	public void setAllowStudentReminders(boolean allowStudentReminders) {
		this.allowStudentReminders = allowStudentReminders;
	}
}
