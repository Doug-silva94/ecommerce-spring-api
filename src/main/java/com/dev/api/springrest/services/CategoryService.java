package com.dev.api.springrest.services;

import com.dev.api.springrest.dtos.CategoryDto;
import com.dev.api.springrest.exceptions.CategoryException;
import com.dev.api.springrest.models.Category;
import com.dev.api.springrest.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	public CategoryDto categoryToDTO(Category category) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());
		categoryDto.setDescription(category.getDescription());
		return categoryDto;
	}
	
	public Category dtoToCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		return category;
	}
	
	public void saveCategory(CategoryDto categoryDto) {
		Category category = dtoToCategory(categoryDto);
		categoryRepository.save(category);
	}

	public Category getCategoryOrElseThrow(Long id) throws CategoryException {
		return this.categoryRepository.findById(id).orElseThrow(CategoryException::new);
	}
	
	public CategoryDto findOneCategory(Long id) throws CategoryException {
		var ex = new CategoryException(new CategoryException());
		return categoryToDTO(this.getCategoryOrElseThrow(id));
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
				.map(this::categoryToDTO)
				.collect(Collectors.toList());
	}

}