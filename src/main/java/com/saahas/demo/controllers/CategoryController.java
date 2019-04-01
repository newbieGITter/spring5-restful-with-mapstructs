package com.saahas.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.saahas.demo.api.v1.model.CategoryDTO;
import com.saahas.demo.api.v1.model.CategoryListDTO;
import com.saahas.demo.services.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/api/v1/categories/")
	public ResponseEntity<CategoryListDTO> listCategories() {
		return new ResponseEntity<CategoryListDTO>(new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/categories/{categoryName}")
	public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String categoryName) {
		return new ResponseEntity<CategoryDTO>(categoryService.getCategoryByName(categoryName), HttpStatus.OK);
	}
	
}
