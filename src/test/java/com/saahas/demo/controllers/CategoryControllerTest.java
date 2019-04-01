package com.saahas.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.saahas.demo.api.v1.model.CategoryDTO;
import com.saahas.demo.services.CategoryService;

public class CategoryControllerTest {

	private static final String CATEGORY_NAME = "Groceries";

	@InjectMocks
	private CategoryController categoryController;

	@Mock
	private CategoryService categoryService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}

	@Test
	public void testGetAllCategories() throws Exception {
		CategoryDTO cat1 = new CategoryDTO();
		cat1.setId(1L);
		cat1.setName(CATEGORY_NAME);
		
		CategoryDTO cat2 = new CategoryDTO();
		cat2.setId(2L);
		cat2.setName("Fruits");
		
		List<CategoryDTO> categories = Arrays.asList(cat1, cat2);
		Mockito.when(categoryService.getAllCategories()).thenReturn(categories);
		
		mockMvc.perform(get("/api/v1/categories/")
				.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(jsonPath("$.categories", Matchers.hasSize(2)));
	}
	
	@Test
	public void testGetCategoryByName() throws Exception {
		CategoryDTO cat1 = new CategoryDTO();
		cat1.setId(1L);
		cat1.setName(CATEGORY_NAME);
		
		Mockito.when(categoryService.getCategoryByName(CATEGORY_NAME)).thenReturn(cat1);
		
		mockMvc.perform(get("/api/v1/categories/Groceries")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.name", Matchers.equalTo(CATEGORY_NAME)));
	}

}
