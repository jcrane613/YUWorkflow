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
	public void sendNextMessage(Long formId, String subject) {
		String nextApproverEmail = "";
		Form form = formRepository.findById(formId).get();
		int currentStep = form.getCurrent();
		int totalSteps = form.getTotalSteps();
		if (currentStep <= totalSteps) { // there are still approvers left
			switch (currentStep) {
			case 2:
				String username = form.getApprover2();
				nextApproverEmail = userRepository.findByUsername(username).get().getEmail();
				break;
			}	
			SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("noreply@yuredteam.com");
	        message.setTo(nextApproverEmail); 
	        message.setSubject(subject); 
	        message.setText("You have a form to approve!");
	        emailSender.send(message);
		}
		else {                          // the workflow has ended
			nextApproverEmail = "jcrane@mail.yu.edu";
			SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("noreply@yuredteam.com");
	        message.setTo(nextApproverEmail); 
	        message.setSubject(subject); 
	        message.setText("The workflow has ended!");
	        emailSender.send(message);
		} 		
	}
	
	@Override
	public void sendHtmlMessage(String to, String subject, String text, String linkUrl) throws MessagingException {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = String.format(
				"<h3>%s</h3>"
				+ "<br></br>"
				+ "<h4> Please click <a href=\"" + linkUrl + "\" >here</a> to process it! </h4>"	
				, text);
		helper.setText(htmlMsg, true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setFrom("yuredteam@gmail.com");
		emailSender.send(mimeMessage);
	}
	
}
