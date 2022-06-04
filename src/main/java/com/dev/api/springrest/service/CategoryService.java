package com.dev.api.springrest.service;

import com.dev.api.springrest.dto.CategoryDto;
import com.dev.api.springrest.exception.CategoryException;
import com.dev.api.springrest.model.Category;
import com.dev.api.springrest.model.Employee;
import com.dev.api.springrest.repository.CategoryRepository;
import com.dev.api.springrest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
   
	@Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EmployeeRepository employee;

    public CategoryDto toDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        Employee employee = new Employee();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setEmployeeId(employee.getId());
        return categoryDto;
    }

    public Category toModel(CategoryDto categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }

    public String saveCategory(CategoryDto categoryDto) {
    	Category category = toModel(categoryDto);
        category.setEmployee(employee.findById(categoryDto.getEmployeeId()).get());
        categoryRepository.save(category);
        return "Category " + category.getId() + " successfully saved!";
    }
    
    public CategoryDto findOneCategory(Long id) throws CategoryException {
    	return categoryRepository.findById(id)
    			.map(category -> toDto(category))
    			.orElseThrow(() -> new CategoryException("Category " + id + " not found. Please, try again!"));
    } 

    public String updateCategory(Long id, CategoryDto categoryDto) throws CategoryException {
        Category dataCategory = this.categoryRepository.findById(id).orElseThrow(() -> 
    	new CategoryException("Category " + id + " was not updated. Please, try again."));
        		
        dataCategory.setName(categoryDto.getName());
        dataCategory.setDescription(categoryDto.getDescription());
        categoryRepository.save(dataCategory);
        return "Category " + id + " successfully updated!";
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    public List<CategoryDto> listAll() {
        return categoryRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

}