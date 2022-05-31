package com.dev.api.springrest.services;

import com.dev.api.springrest.dtos.CategoryDto;
import com.dev.api.springrest.exceptions.CategoryException;
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

	public CategoryDto categoryToDTO(Category category) {

		CategoryDto categoryDTO = new CategoryDto();
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		categoryDTO.setDescription(category.getDescription());
		return categoryDTO;
	}

	public Category dtoToCategory(CategoryDto categoryDTO) {
		Category category = new Category();
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());
		return category;
	}

	public CategoryDto findOneCategory(Long id) throws CategoryException {
		Optional<Category> category = categoryRepository.findById(id);
		Category dataCategory = new Category();
		CategoryDto categoryDTO = new CategoryDto();

		if (category.isPresent()) {
			dataCategory = category.get();
			categoryDTO = categoryToDTO(category.get());
			return categoryDTO;
		}
		throw new CategoryException("Category " + dataCategory.getId() + " not found. Try again, please.");
	}

	public String updateCategory(Long id, CategoryDto categoryDTO) throws CategoryException {
		Optional<Category> category = categoryRepository.findById(id);
		Category dataCategory = new Category();

		if (category.isPresent()) {
			dataCategory = category.get();
			if (categoryDTO.getId() != null) {
				dataCategory.setId(categoryDTO.getId());
			}
			if (categoryDTO.getName() != null) {
				dataCategory.setName(categoryDTO.getName());
			}
			if (categoryDTO.getDescription() != null) {
				dataCategory.setDescription(categoryDTO.getDescription());
			}
			categoryRepository.save(dataCategory);
			return "Category " + dataCategory.getId() + " successfully updated!";
		}
		throw new CategoryException("Category " + dataCategory.getId() + "not found. Please, try again.");
	}

	public void deleteCategory(long id) {
		categoryRepository.deleteById(id);
	}

	public List<CategoryDto> listAll() {
		return categoryRepository.findAll().stream().map(this::categoryToDTO).collect(Collectors.toList());
	}

}
