package com.dev.api.springrest.services;

import com.dev.api.springrest.dtos.CategoryDTO;
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

	public void saveCategory(CategoryDTO categoryDTO) {
		Category category = dtoToCategory(categoryDTO);
		categoryRepository.save(category);
	}
	public CategoryDTO categoryToDTO(Category category){
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		categoryDTO.setDescription(category.getDescription());
		return categoryDTO;
	}
	public Category dtoToCategory(CategoryDTO categoryDTO){
		Category category = new Category();
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());
		return category;
	}
	public CategoryDTO findOneCategory(Long id){
		Optional<Category> category = categoryRepository.findById(id);
		Category categoryOnData;
		CategoryDTO categoryDTO = new CategoryDTO();
		if (category.isPresent()){
			categoryOnData = category.get();
			categoryDTO = categoryToDTO(category.get());
		}
		return categoryDTO;
	}

	public void updateCategory(Long id, CategoryDTO categoryDTO) {
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

	public List<CategoryDTO> listAll() {
		return categoryRepository.findAll()
				.stream()
				.map(this::categoryToDTO)
				.collect(Collectors.toList());
	}


}
