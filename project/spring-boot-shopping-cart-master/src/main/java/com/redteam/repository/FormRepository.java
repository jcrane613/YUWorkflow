package com.redteam.repository;

import com.redteam.model.Form;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form , Long> {
    Optional<Form> findById(Long id);

    Page<Form> findAllByApprover1(Pageable pageable, String approver1);
    Page<Form> findAllByApprover2(Pageable pageable, String approver2);
    List<Form> findAllByApprover1(String approver1);
    List<Form> findAllByApprover2(String approver2);

	List<Form> findAllByStatus(String status);
	
    Optional<Form> findByTrackingId(String trackingId);


}
