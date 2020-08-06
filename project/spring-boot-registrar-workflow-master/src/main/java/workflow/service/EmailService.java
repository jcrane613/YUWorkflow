package workflow.service;

import javax.mail.MessagingException;

public interface EmailService {

	public void sendSimpleMessage(String to, String subject, String text);

	public void sendNextMessage(Long formId) throws MessagingException;
	
	void sendNextChangeTSMessage(Long formId) throws MessagingException;

	void sendNextLeaveOfAbMessage(Long formId) throws MessagingException;

	public void sendNewApprovalHtmlMessage(String to, String linkUrl) throws MessagingException;

	public void sendStudentDenialMessage(String studentEmail, String trackingId, String denyer) throws MessagingException;

	public void sendStudentApprovalMessage(String studentEmail, String trackingId) throws MessagingException;

	public void sendInitialStudentMessage(String studentEmail, String trackingId, String name, String major, String type) throws MessagingException;


}
