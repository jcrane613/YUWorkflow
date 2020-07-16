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
	public void sendNextMessage(Long formId, String subject, String text) {
		String nextApproverEmail = "";
		Form form = formRepository.findById(formId).get();
		int currentStep = form.getCurrent();
		switch (currentStep) {
			case 2:
				String username = form.getApprover2();
				nextApproverEmail = userRepository.findByUsername(username).get().getEmail();
		}
			
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("noreply@yuredteam.com");
        message.setTo(nextApproverEmail); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message); 		
	}
}
