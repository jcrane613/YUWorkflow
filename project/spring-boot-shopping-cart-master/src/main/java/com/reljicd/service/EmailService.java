package com.reljicd.service;

import javax.mail.MessagingException;

public interface EmailService {

	public void sendSimpleMessage(String to, String subject, String text);

	public void sendNextMessage(Long formId);

	public void sendNextHtmlMessage(Long formId, String subject);

	public void sendHtmlMessage(String to, String linkUrl) throws MessagingException;

	public void sendDenialMessage(Long formId);

	public void sendApprovalMessage(Long formId);


}
