package com.dev.api.springrest.services;

import com.dev.api.springrest.dtos.CategoryDto;
import com.dev.api.springrest.models.Category;
import com.dev.api.springrest.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	public void saveCategory(CategoryDto categoryDTO) {
		Category category = dtoToCategory(categoryDTO);
		categoryRepository.save(category);
	}
	public CategoryDto categoryToDTO(Category category){
		CategoryDto categoryDTO = new CategoryDto();
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		categoryDTO.setDescription(category.getDescription());
		return categoryDTO;
	}
	public Category dtoToCategory(CategoryDto categoryDTO){
		Category category = new Category();
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());
		return category;
	}
	public CategoryDto findOneCategory(Long id){
		Optional<Category> category = categoryRepository.findById(id);
		Category categoryOnData;
		CategoryDto categoryDTO = new CategoryDto();
		if (category.isPresent()){
			categoryOnData = category.get();
			categoryDTO = categoryToDTO(category.get());
		}
		return categoryDTO;
	}

	public void updateCategory(Long id, CategoryDto categoryDTO) {
		Category category = categoryRepository.findById(id).orElseThrow();
		if (categoryDTO.getName() != null) {
			category.setName(categoryDTO.getName());
		}
		if (categoryDTO.getDescription() != null) {
			category.setDescription(categoryDTO.getDescription());
		}
		categoryRepository.save(category);
	}

	public void deleteCategory(long id){
		categoryRepository.deleteById(id);
	}

	public List<CategoryDto> listAll() {
		return categoryRepository.findAll()
				.stream()
				.map(this::categoryToDTO)
				.collect(Collectors.toList());
	}


}
