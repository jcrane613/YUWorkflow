package workflow.service.impl;

import workflow.model.LeaveOfAb;
import workflow.repository.LeaveOfAbRepository;
import workflow.service.LeaveOfAbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LeaveOfAbServiceImpl implements LeaveOfAbService {

	LeaveOfAbRepository leaveOfAbRepository;
	@Autowired
	public LeaveOfAbServiceImpl(LeaveOfAbRepository leaveOfAbsRepo) {
		this.leaveOfAbRepository = leaveOfAbsRepo;

	}
	@Override
	public LeaveOfAb saveForm(LeaveOfAb leaveOfAb) {
		return leaveOfAbRepository.saveAndFlush(leaveOfAb);
	}

	@Override
	public Page<LeaveOfAb> findAllFormsPageable(Pageable pageable) {
		return leaveOfAbRepository.findAll(pageable);
	}

	@Override
	public Page<LeaveOfAb> findAllFormsPageableByApprover1(Pageable pageable, String approver1) {
		return leaveOfAbRepository.findAllByApprover1(pageable,approver1);
	}
	@Override
	public Page<LeaveOfAb> findAllFormsPageableByApprover2(Pageable pageable, String approver2) {
		return leaveOfAbRepository.findAllByApprover2(pageable,approver2);
	}
	@Override
	public Page<LeaveOfAb> findAllFormsPageableByApprover3(Pageable pageable, String approver3) {
		return leaveOfAbRepository.findAllByApprover3(pageable,approver3);
	}

	@Override
	public Optional<LeaveOfAb> findById(Long id) {
		return leaveOfAbRepository.findById(id);
	}

	@Override
	public List<LeaveOfAb> findAllFormsByApprover1(String approver1) {
		return leaveOfAbRepository.findAllByApprover1(approver1);
	}

	@Override
	public List<LeaveOfAb> findAllFormsByApprover2(String approver2) {
		return leaveOfAbRepository.findAllByApprover2(approver2);
	}
	@Override
	public List<LeaveOfAb> findAllForms() {
		return leaveOfAbRepository.findAll();
	}
	@Override
	public void withdrawForm(LeaveOfAb leaveOfAb) {
		leaveOfAbRepository.delete(leaveOfAb);
	}

	@Override
	public List<LeaveOfAb> findByName(String name) {
		return leaveOfAbRepository.findByName(name);
	}

	@Override
	public List<LeaveOfAb> findByLastName(String lastName) {
		return leaveOfAbRepository.findByLastName(lastName);
	}
	@Override
	public List<LeaveOfAb> findByYuid(String yuid) {return leaveOfAbRepository.findByYuid(yuid);}

	@Override
	public List<LeaveOfAb> findByEmail(String email) {
		return leaveOfAbRepository.findByEmail(email);
	}

	@Override
	public List<LeaveOfAb> findByLastNameAndName(String lastName, String name) {
		return leaveOfAbRepository.findByLastNameAndName(lastName, name);
	}
}
