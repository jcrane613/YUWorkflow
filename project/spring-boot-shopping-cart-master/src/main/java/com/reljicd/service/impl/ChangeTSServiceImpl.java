
package com.reljicd.service.impl;

import com.reljicd.model.ChangeTS;
import com.reljicd.model.Form;
import com.reljicd.model.Product;
import com.reljicd.model.User;
import com.reljicd.repository.ChangeTSRepository;
import com.reljicd.repository.FormRepository;
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
import java.util.Optional;

@Service
public class ChangeTSServiceImpl implements ChangeTSService {

	ChangeTSRepository formRepository;
	@Autowired
	public ChangeTSServiceImpl(ChangeTSRepository formRepository) {
		this.formRepository = formRepository;

	}
	@Override
	public ChangeTS saveForm(ChangeTS changeTS) {
		return formRepository.saveAndFlush(changeTS);
	}

	@Override
	public Page<ChangeTS> findAllFormsPageable(Pageable pageable) {
		return formRepository.findAll(pageable);
	}

	@Override
	public Page<ChangeTS> findAllFormsPageableByApprover1(Pageable pageable, String approver1) {
		return formRepository.findAllByApprover1(pageable,approver1);
	}
	@Override
	public Page<ChangeTS> findAllFormsPageableByApprover2(Pageable pageable, String approver2) {
		return formRepository.findAllByApprover2(pageable,approver2);
	}

	@Override
	public Optional<ChangeTS> findById(Long id) {
		return formRepository.findById(id);
	}
}
