
package com.reljicd.service.impl;

import com.reljicd.model.ChangeTS;
import com.reljicd.model.Form;
import com.reljicd.model.Product;
import com.reljicd.model.User;
import com.reljicd.repository.ChangeTSRepository;
import com.reljicd.repository.RoleRepository;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.ChangeTSService;
import com.reljicd.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ChangeTSServiceImpl implements ChangeTSService {

	ChangeTSRepository changeTSFormRepository;
	@Autowired
	public ChangeTSServiceImpl(ChangeTSRepository changeTSFormRepository) {
		this.changeTSFormRepository = changeTSFormRepository;

	}
	@Override
	public ChangeTS saveForm(ChangeTS changeTS) {
		return changeTSFormRepository.saveAndFlush(changeTS);
	}

	@Override
	public Page<ChangeTS> findAllFormsPageable(Pageable pageable) {
		return changeTSFormRepository.findAll(pageable);
	}

	@Override
	public Page<ChangeTS> findAllFormsPageableByApprover1(Pageable pageable, String approver1) {
		return changeTSFormRepository.findAllByApprover1(pageable,approver1);
	}
	@Override
	public Page<ChangeTS> findAllFormsPageableByApprover2(Pageable pageable, String approver2) {
		return changeTSFormRepository.findAllByApprover2(pageable,approver2);
	}

	@Override
	public List<ChangeTS> findAllFormsByApprover1(String approver1) {
		return changeTSFormRepository.findAllByApprover1(approver1);
	}

	@Override
	public List<ChangeTS> findAllFormsByApprover2(String approver2) {
		return changeTSFormRepository.findAllByApprover2(approver2);
	}

	@Override
	public Optional<ChangeTS> findById(Long id) {
		return changeTSFormRepository.findById(id);
	}
	@Override
	public List<ChangeTS> findAllForms() {
		return changeTSFormRepository.findAll();
	}
}
