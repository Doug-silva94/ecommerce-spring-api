package com.dev.api.springrest.controllers;

import com.dev.api.springrest.dtos.CategoryDTO;
import com.dev.api.springrest.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {this.categoryService = categoryService;}

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> listAll() {
        return ResponseEntity.ok(categoryService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(categoryService.findOneCategory(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable long id, @RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<Void> createCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.saveCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
