package com.reljicd.service.impl;

import com.reljicd.model.Form;
import com.reljicd.model.LeaveOfAb;
import com.reljicd.repository.FormRepository;
import com.reljicd.repository.LeaveOfAbRepository;
import com.reljicd.service.FormService;
import com.reljicd.service.LeaveOfAbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LeaveOfAbServiceImpl implements LeaveOfAbService {

	LeaveOfAbRepository formRepository;
	@Autowired
	public LeaveOfAbServiceImpl(LeaveOfAbRepository formRepository) {
		this.formRepository = formRepository;

	}
	@Override
	public LeaveOfAb saveForm(LeaveOfAb leaveOfAb) {
		return formRepository.saveAndFlush(leaveOfAb);
	}

	@Override
	public Page<LeaveOfAb> findAllFormsPageable(Pageable pageable) {
		return formRepository.findAll(pageable);
	}

	@Override
	public Page<LeaveOfAb> findAllFormsPageableByApprover1(Pageable pageable, String approver1) {
		return formRepository.findAllByApprover1(pageable,approver1);
	}
	@Override
	public Page<LeaveOfAb> findAllFormsPageableByApprover2(Pageable pageable, String approver2) {
		return formRepository.findAllByApprover2(pageable,approver2);
	}

	@Override
	public Optional<LeaveOfAb> findById(Long id) {
		return formRepository.findById(id);
	}
}
