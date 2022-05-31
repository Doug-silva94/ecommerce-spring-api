package com.dev.api.springrest.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.api.springrest.dtos.CategoryDto;
import com.dev.api.springrest.models.Category;
import com.dev.api.springrest.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	public void saveCategory(CategoryDto categoryDto) {
		Category category = dtoToCategory(categoryDto);
		categoryRepository.save(category);
	}
	public CategoryDto categoryToDto(Category category){
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());
		categoryDto.setDescription(category.getDescription());
		return categoryDto;
	}
	public Category dtoToCategory(CategoryDto categoryDto){
		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		return category;
	}
	
	public CategoryDto findOneCategory(Long id){
		Optional<Category> category = categoryRepository.findById(id);
		Category categoryOnData;
		CategoryDto categoryDTO = new CategoryDto();
		if (category.isPresent()){
			categoryOnData = category.get();
			categoryDTO = categoryToDto(category.get());
		}
		return categoryDTO;
	}

	public void updateCategory(Long id, CategoryDto categoryDto) {
		Category category = categoryRepository.findById(id).orElseThrow();
		if (categoryDto.getName() != null) {
			category.setName(categoryDto.getName());
		}
		if (categoryDto.getDescription() != null) {
			category.setDescription(categoryDto.getDescription());
		}
		categoryRepository.save(category);
	}

	public void deleteCategory(long id){
		categoryRepository.deleteById(id);
	}

	public List<CategoryDto> listAll() {
		return categoryRepository.findAll()
				.stream()
				.map(this::categoryToDto)
				.collect(Collectors.toList());
	}


}
