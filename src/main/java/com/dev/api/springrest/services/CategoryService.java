package com.dev.api.springrest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.api.springrest.dtos.CategoryDTO;
import com.dev.api.springrest.models.Category;
import com.dev.api.springrest.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	public void saveCategory(CategoryDTO categoryDTO) {
		Category category = dtoToCategory(categoryDTO);
		categoryRepository.save(category);
	}

	public CategoryDTO categoryToDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		categoryDTO.setDescription(category.getDescription());
		return categoryDTO;
	}

	public Category dtoToCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());
		return category;
	}

	public CategoryDTO findOneCategory(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		Category categoryOnData;
		CategoryDTO categoryDTO = new CategoryDTO();
		if (category.isPresent()) {
			categoryOnData = category.get();
			categoryDTO = categoryToDTO(category.get());
		}
		return categoryDTO;
	}

	public void updateCategory(Long id, CategoryDTO categoryDTO) {
		Optional<Category> category = categoryRepository.findById(id);
		Category categoryOnBank = new Category();
		if (category.isPresent()) {
			categoryOnBank = category.get();
			if (categoryDTO.getName() != null) {
				categoryOnBank.setName(categoryDTO.getName());
			}
			if (categoryDTO.getDescription() != null) {
				categoryOnBank.setDescription(categoryDTO.getDescription());
			}
			categoryRepository.save(categoryOnBank);
		}
	}

	public void deleteCategory(long id) {
		categoryRepository.deleteById(id);
	}

	public List<CategoryDTO> listAll() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDTO> categoryDTOS = new ArrayList<>();

		for (Category category : categories) {
			CategoryDTO categoryDTO = categoryToDTO(category);
			categoryDTOS.add(categoryDTO);
		}
		return categoryDTOS;
	}

}
