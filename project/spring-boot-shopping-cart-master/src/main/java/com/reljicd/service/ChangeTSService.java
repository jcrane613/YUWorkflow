package com.reljicd.service;

import com.reljicd.model.ChangeTS;
import com.reljicd.model.Form;
import com.reljicd.model.Product;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChangeTSService {

	Optional<ChangeTS> findById(Long id);
	ChangeTS saveForm(ChangeTS changeTS);
	Page<ChangeTS> findAllFormsPageable(Pageable pageable);
	Page<ChangeTS> findAllFormsPageableByApprover1(Pageable pageable , String approver1);
	Page<ChangeTS> findAllFormsPageableByApprover2(Pageable pageable , String approver2);
}
