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

	LeaveOfAbRepository leaveOfAbsRepo;
	@Autowired
	public LeaveOfAbServiceImpl(LeaveOfAbRepository leaveOfAbsRepo) {
		this.leaveOfAbsRepo = leaveOfAbsRepo;

	}
	@Override
	public LeaveOfAb saveForm(LeaveOfAb leaveOfAb) {
		return leaveOfAbsRepo.saveAndFlush(leaveOfAb);
	}

	@Override
	public Page<LeaveOfAb> findAllFormsPageable(Pageable pageable) {
		return leaveOfAbsRepo.findAll(pageable);
	}

	@Override
	public Page<LeaveOfAb> findAllFormsPageableByApprover1(Pageable pageable, String approver1) {
		return leaveOfAbsRepo.findAllByApprover1(pageable,approver1);
	}
	@Override
	public Page<LeaveOfAb> findAllFormsPageableByApprover2(Pageable pageable, String approver2) {
		return leaveOfAbsRepo.findAllByApprover2(pageable,approver2);
	}

	@Override
	public Optional<LeaveOfAb> findById(Long id) {
		return leaveOfAbsRepo.findById(id);
	}

	@Override
	public List<LeaveOfAb> findAllFormsByApprover1(String approver1) {
		return leaveOfAbsRepo.findAllByApprover1(approver1);
	}

	@Override
	public List<LeaveOfAb> findAllFormsByApprover2(String approver2) {
		return leaveOfAbsRepo.findAllByApprover2(approver2);
	}
}
