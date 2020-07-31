package Workflow.service;

import Workflow.model.LeaveOfAb;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LeaveOfAbService {

	Optional<LeaveOfAb> findById(Long id);
	LeaveOfAb saveForm(LeaveOfAb leaveOfAb);
	Page<LeaveOfAb> findAllFormsPageable(Pageable pageable);
	Page<LeaveOfAb> findAllFormsPageableByApprover1(Pageable pageable , String approver1);
	Page<LeaveOfAb> findAllFormsPageableByApprover2(Pageable pageable , String approver2);
	Page<LeaveOfAb> findAllFormsPageableByApprover3(Pageable pageable , String approver3);

	List<LeaveOfAb> findAllForms();
	List<LeaveOfAb> findAllFormsByApprover1(String approver1);
	List<LeaveOfAb> findAllFormsByApprover2(String approver2);
	void withdrawForm(LeaveOfAb leaveOfAb);
}
