package com.reljicd.service;

import com.reljicd.model.Form;
import com.reljicd.model.Product;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FormService {

    Optional<Form> findById(Long id);

	Form saveForm(Form form);
	Page<Form> findAllFormsPageable(Pageable pageable);

}
