package com.saahas.demo.services;

import java.util.List;

import com.saahas.demo.api.v1.model.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> getAllCategories();
	
	CategoryDTO getCategoryByName(String name);
}
