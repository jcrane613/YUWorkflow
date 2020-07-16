package com.reljicd.service;

public interface EmailService {

	public void sendSimpleMessage(String to, String subject, String text);

	public void sendNextMessage(Long formId, String subject, String text);

}
