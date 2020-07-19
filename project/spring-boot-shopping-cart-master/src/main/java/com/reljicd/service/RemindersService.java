package com.reljicd.service;

import javax.mail.MessagingException;

public interface RemindersService {

	public void sendAllReminders() throws MessagingException;

}
