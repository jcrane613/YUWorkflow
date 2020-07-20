package com.reljicd.service;

import com.reljicd.model.ChangeTS;
import com.reljicd.model.Form;
import com.reljicd.model.LeaveOfAb;
import com.reljicd.model.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LeaveOfAbService {

	Optional<LeaveOfAb> findById(Long id);
	LeaveOfAb saveForm(LeaveOfAb leaveOfAb);
	Page<LeaveOfAb> findAllFormsPageable(Pageable pageable);
	Page<LeaveOfAb> findAllFormsPageableByApprover1(Pageable pageable , String approver1);
	Page<LeaveOfAb> findAllFormsPageableByApprover2(Pageable pageable , String approver2);

	List<LeaveOfAb> findAllFormsByApprover1(String approver1);
	List<LeaveOfAb> findAllFormsByApprover2(String approver2);
}
