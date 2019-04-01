package com.saahas.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.saahas.demo.api.v1.model.CategoryDTO;
import com.saahas.demo.api.vi.mapper.CategoryMapper;
import com.saahas.demo.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepo;
	private CategoryMapper mapper = CategoryMapper.INSTANCE;
	
	public CategoryServiceImpl(CategoryMapper mapper, CategoryRepository categoryRepository) {
		super();
		this.mapper = mapper;
		this.categoryRepo = categoryRepository;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		return categoryRepo.findAll().stream().map(s -> mapper.categoryToCategoryDTO(s)).collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
		return mapper.categoryToCategoryDTO(categoryRepo.findByName(name));
	}

}
