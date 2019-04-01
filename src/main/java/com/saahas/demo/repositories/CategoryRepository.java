package com.saahas.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saahas.demo.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Category findByName(String name);

}
