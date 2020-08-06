package workflow.service;

import workflow.model.ChangeTS;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ChangeTSService {

	Optional<ChangeTS> findById(Long id);
	ChangeTS saveForm(ChangeTS changeTS);
	Page<ChangeTS> findAllFormsPageable(Pageable pageable);
	Page<ChangeTS> findAllFormsPageableByApprover1(Pageable pageable , String approver1);
	Page<ChangeTS> findAllFormsPageableByApprover2(Pageable pageable , String approver2);
	Page<ChangeTS> findAllFormsPageableByApprover3(Pageable pageable , String approver3);

	List<ChangeTS> findAllForms();
	List<ChangeTS> findAllFormsByApprover1(String approver1);
	List<ChangeTS> findAllFormsByApprover2(String approver2);
	List<ChangeTS> findAllFormsByApprover3(String approver3);
	void withdrawForm(ChangeTS changeTS);

	//Query Searches
	List<ChangeTS> findByName(String name);
	List<ChangeTS> findByLastName(String lastName);
	List<ChangeTS> findByYuid(String yuid);
	List<ChangeTS> findByEmail(String email);
	List<ChangeTS> findByLastNameAndName(String lastName, String name);
	List<ChangeTS> findByNameContains(String name);

}
