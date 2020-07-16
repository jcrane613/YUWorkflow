package com.reljicd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.reljicd.service.EmailService;

@Component
public class EmailServiceImpl implements EmailService {
 
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
}
