package com.dev.api.springrest.controllers;

import com.dev.api.springrest.dtos.ProductDto;
import com.dev.api.springrest.exceptions.ProductException;
import com.dev.api.springrest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping()
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) throws Exception {
        productService.saveProduct(productDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable long id) throws ProductException {
        return ResponseEntity.ok(productService.findOneProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable long id, @RequestBody ProductDto productDto) throws ProductException {
        productService.updateProduct(id, productDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> listAll() {
        return ResponseEntity.ok(productService.listAll());
    }

}
