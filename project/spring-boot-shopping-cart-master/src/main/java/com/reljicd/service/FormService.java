package com.reljicd.service;

import com.reljicd.model.Form;
import com.reljicd.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FormService {

	Form saveForm(Form form);
	Page<Form> findAllFormsPageable(Pageable pageable);

}
