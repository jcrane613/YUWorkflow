package com.reljicd.service.impl;

import com.reljicd.model.ChangeTS;
import com.reljicd.model.Form;
import com.reljicd.model.LeaveOfAb;
import com.reljicd.repository.LeaveOfAbRepository;
import com.reljicd.service.FormService;
import com.reljicd.service.LeaveOfAbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LeaveOfAbServiceImpl implements LeaveOfAbService {

	LeaveOfAbRepository leaveOfAbRepository;
	@Autowired
	public LeaveOfAbServiceImpl(LeaveOfAbRepository leaveOfAbsRepo) {
		this.leaveOfAbRepository = leaveOfAbsRepo;

	}
	@Override
	public LeaveOfAb saveForm(LeaveOfAb leaveOfAb) {
		return leaveOfAbRepository.saveAndFlush(leaveOfAb);
	}

	@Override
	public Page<LeaveOfAb> findAllFormsPageable(Pageable pageable) {
		return leaveOfAbRepository.findAll(pageable);
	}

	@Override
	public Page<LeaveOfAb> findAllFormsPageableByApprover1(Pageable pageable, String approver1) {
		return leaveOfAbRepository.findAllByApprover1(pageable,approver1);
	}
	@Override
	public Page<LeaveOfAb> findAllFormsPageableByApprover2(Pageable pageable, String approver2) {
		return leaveOfAbRepository.findAllByApprover2(pageable,approver2);
	}
	@Override
	public Page<LeaveOfAb> findAllFormsPageableByApprover3(Pageable pageable, String approver3) {
		return leaveOfAbRepository.findAllByApprover3(pageable,approver3);
	}

	@Override
	public Optional<LeaveOfAb> findById(Long id) {
		return leaveOfAbRepository.findById(id);
	}

	@Override
	public List<LeaveOfAb> findAllFormsByApprover1(String approver1) {
		return leaveOfAbRepository.findAllByApprover1(approver1);
	}

	@Override
	public List<LeaveOfAb> findAllFormsByApprover2(String approver2) {
		return leaveOfAbRepository.findAllByApprover2(approver2);
	}
	@Override
	public List<LeaveOfAb> findAllForms() {
		return leaveOfAbRepository.findAll();
	}
}
