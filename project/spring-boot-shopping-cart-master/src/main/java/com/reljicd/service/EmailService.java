package com.reljicd.service;

import javax.mail.MessagingException;

public interface EmailService {

	public void sendSimpleMessage(String to, String subject, String text);

	public void sendNextMessage(Long formId, String subject);

	void sendNextHtmlMessage(Long formId, String subject);

	void sendHtmlMessage(String to, String linkUrl) throws MessagingException;

}
