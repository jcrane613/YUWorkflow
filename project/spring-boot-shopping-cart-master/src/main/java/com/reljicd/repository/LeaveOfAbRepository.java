package com.reljicd.repository;

import com.reljicd.model.ChangeTS;
import com.reljicd.model.Form;
import com.reljicd.model.LeaveOfAb;
import com.reljicd.model.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveOfAbRepository extends JpaRepository<LeaveOfAb, Long> {
	Optional<LeaveOfAb> findById(Long id);
	Page<LeaveOfAb> findAllByApprover1(Pageable pageable, String approver1);
	Page<LeaveOfAb> findAllByApprover2(Pageable pageable, String approver2);
	List<LeaveOfAb> findAllByStatus(String status);

	List<LeaveOfAb> findAllByApprover1(String approver1);
	List<LeaveOfAb> findAllByApprover2(String approver2);
}
