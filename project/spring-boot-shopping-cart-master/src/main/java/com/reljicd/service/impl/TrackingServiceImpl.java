package com.reljicd.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reljicd.model.ChangeTS;
import com.reljicd.model.Form;
import com.reljicd.model.LeaveOfAb;
import com.reljicd.repository.ChangeTSRepository;
import com.reljicd.repository.FormRepository;
import com.reljicd.repository.LeaveOfAbRepository;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.*;

@Service
public class TrackingServiceImpl implements TrackingService {

	private final FormRepository formRepository;
	private final ChangeTSRepository changeTSRepository;
	private final LeaveOfAbRepository leaveOfAbRepository;

    @Autowired
    public TrackingServiceImpl(FormRepository formRepository, ChangeTSRepository changeTSRepository, LeaveOfAbRepository leaveOfAbRepository) {
        this.formRepository = formRepository;
        this.changeTSRepository = changeTSRepository;
        this.leaveOfAbRepository = leaveOfAbRepository;
    }

	@Override
	public Form getFormByTrackingId(String trackingId) {
		Form form = formRepository.findByTrackingId(trackingId).orElse(null);
		return form;
	}
	
	@Override
	public ChangeTS getChangeTSFormByTrackingId(String trackingId) {
		ChangeTS form = changeTSRepository.findByTrackingId(trackingId).orElse(null);
		return form;
	}
	
	@Override
	public LeaveOfAb getLeaveOfAbFormByTrackingId(String trackingId) {
		LeaveOfAb form = leaveOfAbRepository.findByTrackingId(trackingId).orElse(null);
		return form;
	}
	
	
	
}
