package com.reljicd.service;

import com.reljicd.model.Form;
import com.reljicd.model.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FormService {

    Optional<Form> findById(Long id);

	Form saveForm(Form form);
	Page<Form> findAllFormsPageable(Pageable pageable);
	Page<Form> findAllFormsPageableByApprover1(Pageable pageable , String approver1);
	Page<Form> findAllFormsPageableByApprover2(Pageable pageable , String approver2);

	List<Form> findAllFormsByApprover1(String approver1);
	List<Form> findAllFormsByApprover2(String approver2);




}
