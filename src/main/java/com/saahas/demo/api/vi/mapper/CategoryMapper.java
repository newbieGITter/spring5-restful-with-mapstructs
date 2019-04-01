package com.saahas.demo.api.vi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.saahas.demo.api.v1.model.CategoryDTO;
import com.saahas.demo.domain.Category;

@Mapper
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
    CategoryDTO categoryToCategoryDTO(Category category);
}
