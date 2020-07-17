package com.reljicd.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.reljicd.model.Form;
import com.reljicd.model.Product;
import com.reljicd.repository.FormRepository;
import com.reljicd.repository.ProductRepository;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.EmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

@Component
public class EmailServiceImpl implements EmailService {
 
	private final FormRepository formRepository;
	private final UserRepository userRepository;

    @Autowired
    public EmailServiceImpl(UserRepository userRepository, FormRepository formRepository) {
        this.userRepository = userRepository;
        this.formRepository = formRepository;
    }
	
    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    private SimpleMailMessage templateSimpleMessage;
 
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("noreply@yuredteam.com");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message); 
    }
    
    public void sendSimpleTemplatedMessage(String to, String subject, String text) { 
        SimpleMailMessage message = this.templateSimpleMessage; 
        message.setFrom("noreply@yuredteam.com");
        message.setTo(to); 
        message.setSubject(subject); 
        String formattedText = String.format(message.getText(), text);
        message.setText(formattedText);
        emailSender.send(message);
    }

	@Override
	public void sendNextMessage(Long formId) throws MessagingException {
		String nextApproverEmail = "";
		Form form = formRepository.findById(formId).get();
		int currentStep = form.getCurrent();
		int totalSteps = form.getTotalSteps();
		if (currentStep <= totalSteps) { // the workflow is still live
			switch (currentStep) {
			case 2:
				String username = form.getApprover2();
				nextApproverEmail = userRepository.findByUsername(username).get().getEmail();
				break;
			}	
			this.sendHtmlMessage(nextApproverEmail, "http://localhost:8070/shoppingCart/processForm/"+form.getId());
		}
		else {                          // the workflow has ended
			nextApproverEmail = "yaircaplan@gmail.com";
			String text = String.format("Form #%d has just been completely approved", form.getId());
			this.sendSimpleMessage(nextApproverEmail, "Regstrar Form Completion Notification", text);
			this.sendSimpleMessage(form.getStudentEmail(), "Regstrar Form Completed", text);
		} 		
	}
	 
	@Override
	public void sendHtmlMessage(String to, String linkUrl) throws MessagingException {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = String.format(
				"<h3>You have a new approval!</h3>"
				+ "<br></br>"
				+ "<h4> Please click <a href=\"%s\">here</a> to process it! </h4>"	
				, linkUrl);
		helper.setText(htmlMsg, true);
		helper.setTo(to);
		helper.setSubject("Registrar Forms Update");
		helper.setFrom("yuredteam@gmail.com");
		emailSender.send(mimeMessage);
	}

	@Override
	public void sendDenialMessage(Long formId) {
		Form form =  formRepository.findById(formId).get();
		String studentEmail = form.getStudentEmail();
		String text = String.format("Your registrar form #%d has been denied by %s.", form.getId(), form.getDenyer());
		this.sendSimpleMessage(studentEmail, "Registrar Form Denied", text);
	}
	
	@Override
	public void sendApprovalMessage(Long formId) {
		Form form =  formRepository.findById(formId).get();
		String studentEmail = form.getStudentEmail();
		String text = String.format("Your registrar form #%d has been fully approved!", form.getId());
		this.sendSimpleMessage(studentEmail, "Registrar Form Approved", text);
	}
	
}
