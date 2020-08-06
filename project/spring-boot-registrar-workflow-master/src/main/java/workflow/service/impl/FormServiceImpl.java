package workflow.service.impl;

import workflow.model.Form;
import workflow.repository.FormRepository;
import workflow.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
	public Page<Form> findAllFormsPageableByApprover1(Pageable pageable, String approver1) {
		return formRepository.findAllByApprover1(pageable,approver1);
	}
	@Override
	public Page<Form> findAllFormsPageableByApprover2(Pageable pageable, String approver2) {
		return formRepository.findAllByApprover2(pageable,approver2);
	}

	@Override
	public List<Form> findAllFormsByApprover1(String approver1) {
		return formRepository.findAllByApprover1(approver1);
	}
	@Override
	public List<Form> findAllFormsByApprover2(String approver2) {
		return formRepository.findAllByApprover2(approver2);
	}

	@Override
	public Optional<Form> findById(Long id) {
		return formRepository.findById(id);
	}
	@Override
	public List<Form> findAllForms() {
		return formRepository.findAll();
	}
	@Override
	public void withdrawForm(Form form) {
		formRepository.delete(form);		
	}

	@Override
	public List<Form> findByName(String name) {
		return formRepository.findByName(name);
	}

	@Override
	public List<Form> findByLastName(String lastName) {
		return formRepository.findByLastName(lastName);
	}


	@Override
	public List<Form> findByYuid(String yuid) {return formRepository.findByYuid(yuid);}
	@Override
	public List<Form> findByEmail(String email) {
		return formRepository.findByEmailContaining(email);
	}

	@Override
	public List<Form> findByLastNameAndName(String lastName, String name) {
		return formRepository.findByLastNameAndName(lastName, name);
	}
}
