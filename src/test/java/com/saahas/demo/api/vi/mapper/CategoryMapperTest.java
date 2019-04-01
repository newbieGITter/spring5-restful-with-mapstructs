package com.saahas.demo.api.vi.mapper;

import org.junit.Assert;
import org.junit.Test;

import com.saahas.demo.api.v1.model.CategoryDTO;
import com.saahas.demo.domain.Category;

public class CategoryMapperTest {

	CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
	
	@Test
	public void test() {
		Category cat = new Category();
		cat.setId(1L);
		cat.setName("sprouts");
		
		CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(cat);
		
		Assert.assertNotNull(categoryDTO);
		Assert.assertEquals("sprouts", categoryDTO.getName());
	}

}
