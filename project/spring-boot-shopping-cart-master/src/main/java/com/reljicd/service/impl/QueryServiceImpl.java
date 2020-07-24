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
public class QueryServiceImpl implements QueryService {

	private final FormRepository formRepository;
	private final ChangeTSRepository changeTSRepository;
	private final LeaveOfAbRepository leaveOfAbRepository;

	@Autowired
	public QueryServiceImpl(FormRepository formRepository, ChangeTSRepository changeTSRepository, LeaveOfAbRepository leaveOfAbRepository) {
		this.formRepository = formRepository;
		this.changeTSRepository = changeTSRepository;
		this.leaveOfAbRepository = leaveOfAbRepository;
	}

	@Override
	public Form getFormsByName(String name) {
		return formRepository.findByName(name).orElse(null);
	}

	@Override
	public ChangeTS getChangeTSFormByName(String name) {
		return changeTSRepository.findByName(name).orElse(null);
	}

	@Override
	public LeaveOfAb getLeaveOfAbFormByName(String name) {
		return leaveOfAbRepository.findByName(name).orElse(null);
	}
}
