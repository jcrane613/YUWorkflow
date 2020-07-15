package com.reljicd.service.impl;

import com.reljicd.model.Form;
import com.reljicd.model.Product;
import com.reljicd.model.User;
import com.reljicd.repository.FormRepository;
import com.reljicd.repository.RoleRepository;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
@Service
public class FormServiceImpl implements FormService {

	FormRepository formRepository;
	@Autowired
	public FormServiceImpl(FormRepository formRepository) {
		this.formRepository = formRepository;

	}
	@Override
	public Form saveForm(Form form) {
		return formRepository.saveAndFlush(form);
	}

	@Override
	public Page<Form> findAllFormsPageable(Pageable pageable) {
		return formRepository.findAll(pageable);
	}

	@Override
	public Page<Form> findAllFormsPageableByApprover(Pageable pageable, String approver) {
		return formRepository.findAllByApprover(pageable,approver);
	}

	@Override
	public Optional<Form> findById(Long id) {
		return formRepository.findById(id);
	}
}
