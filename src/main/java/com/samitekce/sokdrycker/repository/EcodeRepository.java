package com.samitekce.sokdrycker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samitekce.sokdrycker.domain.Ecode;

public interface EcodeRepository extends JpaRepository<Ecode, String> {

	List<Ecode> findByCode(String code);

	List<Ecode> findByDescription(String description);

	List<Ecode> findAllByCode(String code);

}
