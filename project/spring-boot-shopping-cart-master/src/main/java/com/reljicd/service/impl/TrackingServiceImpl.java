package com.reljicd.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reljicd.model.Form;
import com.reljicd.repository.FormRepository;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.*;

@Service
public class TrackingServiceImpl implements TrackingService {

	private final FormRepository formRepository;

    @Autowired
    public TrackingServiceImpl(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

	@Override
	public Form getFormByTrackingId(String trackingId) {
		Form form = formRepository.findByTrackingId(trackingId).orElse(null);
		return form;
	}
	
	
	
}
