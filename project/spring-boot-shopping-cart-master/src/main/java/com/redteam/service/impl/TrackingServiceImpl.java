package com.redteam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redteam.model.ChangeTS;
import com.redteam.model.Form;
import com.redteam.model.LeaveOfAb;
import com.redteam.repository.ChangeTSRepository;
import com.redteam.repository.FormRepository;
import com.redteam.repository.LeaveOfAbRepository;
import com.redteam.service.*;

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
