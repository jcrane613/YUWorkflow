package com.reljicd.repository;

import com.reljicd.model.Form;
import com.reljicd.model.Product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form , Long> {
    Optional<Form> findById(Long id);

}
