package com.dev.api.springrest.controller;

import com.dev.api.springrest.dto.CategoryDto;
import com.dev.api.springrest.exception.CategoryException;
import com.dev.api.springrest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<Void> createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.saveCategory(categoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable long id) throws CategoryException {
        return ResponseEntity.ok(categoryService.findOneCategory(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable long id, @RequestBody CategoryDto categoryDto) {
        categoryService.updateCategory(id, categoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping()
    public ResponseEntity<List<CategoryDto>> listAll() {
        return ResponseEntity.ok(categoryService.listAll());
    }

}
