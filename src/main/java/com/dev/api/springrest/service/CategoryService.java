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

    public CategoryDto categoryToDTO(Category category) {
        CategoryDto categoryDTO = new CategoryDto();
        Employee employee = new Employee();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setEmployeeId(employee.getId());
        return categoryDTO;
    }

    public Category dtoToCategory(CategoryDto categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }

    public void saveCategory(CategoryDto categoryDTO) throws CategoryException {
        Category category = dtoToCategory(categoryDTO);
        category.setEmployee(employee.findById(categoryDTO.getEmployeeId()).get());
        categoryRepository.save(category);
    }

    public Category getCategoryOrElseThrow(Long id) throws CategoryException {
        return this.categoryRepository.findById(id).orElseThrow(CategoryException::new);
    }

    public CategoryDto findOneCategory(Long id) throws CategoryException {
        var ex = new CategoryException(new CategoryException());
        return categoryToDTO(this.getCategoryOrElseThrow(id));
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

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    public List<CategoryDto> listAll() {
        return categoryRepository.findAll().stream().map(this::categoryToDTO).collect(Collectors.toList());
    }


}
