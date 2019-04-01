package com.saahas.demo.services;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.saahas.demo.api.v1.model.CategoryDTO;
import com.saahas.demo.api.vi.mapper.CategoryMapper;
import com.saahas.demo.domain.Category;
import com.saahas.demo.repositories.CategoryRepository;

public class CategoryServiceImplTest {

	private static final String CATEGORY_NAME = "Groceries";

	private CategoryService categoryService;
	
	@Mock
	private CategoryRepository categoryRepo;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepo);
	}
	
	@Test
	public void testGetAllCategories() {
		List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
		Mockito.when(categoryRepo.findAll()).thenReturn(categories);
		
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();
		
		Assert.assertNotNull(categoryDTOs);
		Assert.assertEquals(3, categoryDTOs.size());
	}
	
	@Test
	public void testGetCategoryByName() {
		Category category = new Category();
		category.setId(1L);
		category.setName(CATEGORY_NAME);
		
		Mockito.when(categoryRepo.findByName(CATEGORY_NAME)).thenReturn(category);
		
		CategoryDTO categoryDTO = categoryService.getCategoryByName(CATEGORY_NAME);
		
		Assert.assertNotNull(categoryDTO);
		Assert.assertEquals(CATEGORY_NAME, categoryDTO.getName());
	}

}
