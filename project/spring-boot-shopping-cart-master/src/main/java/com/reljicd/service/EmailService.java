package com.reljicd.service;

import javax.mail.MessagingException;

import com.reljicd.model.Form;

public interface EmailService {

	public void sendSimpleMessage(String to, String subject, String text);

	public void sendNextMessage(Long formId) throws MessagingException;

	public void sendNewApprovalHtmlMessage(String to, String linkUrl) throws MessagingException;

	public void sendStudentDenialMessage(Long formId);

	public void sendStudentApprovalMessage(Long formId);

	public void sendInitialStudentMessage(Form form) throws MessagingException;


}
