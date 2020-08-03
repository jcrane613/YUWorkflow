package workflow.service;

import javax.mail.MessagingException;

public interface RemindersService {

	public void sendAllReminders() throws MessagingException;

	public void sendReminder(String trackingId) throws MessagingException;

}
