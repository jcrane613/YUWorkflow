
package workflow.service.impl;

import workflow.model.ChangeTS;
import workflow.repository.ChangeTSRepository;
import workflow.service.ChangeTSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
	public Page<ChangeTS> findAllFormsPageableByApprover3(Pageable pageable, String approver3) {
		return changeTSFormRepository.findAllByApprover3(pageable,approver3);
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
	public List<ChangeTS> findAllFormsByApprover3(String approver3) {
		return changeTSFormRepository.findAllByApprover3(approver3);
	}

	@Override
	public Optional<ChangeTS> findById(Long id) {
		return changeTSFormRepository.findById(id);
	}
	@Override
	public List<ChangeTS> findAllForms() {
		return changeTSFormRepository.findAll();
	}
	@Override
	public void withdrawForm(ChangeTS changeTS) {
		changeTSFormRepository.delete(changeTS);
	}

	@Override
	public List<ChangeTS> findByName(String name) {
		return changeTSFormRepository.findByName(name);
	}

	@Override
	public List<ChangeTS> findByLastName(String lastName) {
		return changeTSFormRepository.findByLastName(lastName);
	}

	@Override
	public List<ChangeTS> findByYuid(String yuid) {return changeTSFormRepository.findByYuid(yuid);}

	@Override
	public List<ChangeTS> findByEmail(String email) {
		return changeTSFormRepository.findByEmail(email);
	}


	@Override
	public List<ChangeTS> findByLastNameAndName(String lastName, String name) {
		return changeTSFormRepository.findByLastNameAndName(lastName, name);
	}
	@Override
	public List<ChangeTS> findByNameContains(String name) {
		return changeTSFormRepository.findByNameContains(name);
	}

}
