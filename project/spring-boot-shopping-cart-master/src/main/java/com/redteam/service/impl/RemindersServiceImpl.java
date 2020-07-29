package com.redteam.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redteam.model.Form;
import com.redteam.repository.FormRepository;
import com.redteam.service.*;

@Service
public class RemindersServiceImpl implements RemindersService {

	private final FormRepository formRepository;
	private final EmailService emailService;

    @Autowired
    public RemindersServiceImpl(FormRepository formRepository, EmailService emailService) {
        this.formRepository = formRepository;
        this.emailService = emailService;
    }
	
	@Override
	public void sendAllReminders() throws MessagingException {
		List<Form> allOpenForms = formRepository.findAllByStatus("OPEN");
		for (Form form: allOpenForms) {
			emailService.sendNextMessage(form.getId());
		}
		if (allOpenForms.isEmpty()) System.out.println("no forms retrieved");
		
	}

	@Override
	public void sendReminder(String trackingId) throws MessagingException {
		Form form = formRepository.findByTrackingId(trackingId).get(); 
		emailService.sendNextMessage(form.getId());
	}
	
}
