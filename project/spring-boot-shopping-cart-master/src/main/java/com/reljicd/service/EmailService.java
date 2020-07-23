package com.reljicd.service;

import javax.mail.MessagingException;

import com.reljicd.model.ChangeTS;
import com.reljicd.model.Form;
import com.reljicd.model.LeaveOfAb;

public interface EmailService {

	public void sendSimpleMessage(String to, String subject, String text);

	public void sendNextMessage(Long formId) throws MessagingException;

	public void sendNewApprovalHtmlMessage(String to, String linkUrl) throws MessagingException;

	public void sendStudentDenialMessage(Long formId) throws MessagingException;

	public void sendStudentApprovalMessage(Long formId) throws MessagingException;

	public void sendInitialStudentMessage(String studentEmail, String trackingId) throws MessagingException;

}
