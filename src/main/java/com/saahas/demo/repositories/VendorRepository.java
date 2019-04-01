package com.saahas.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saahas.demo.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

	Vendor getById(Long id);
	
	Vendor getByName(String name);
}
