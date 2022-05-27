package com.dev.api.springrest.controllers;

import com.dev.api.springrest.dtos.ProductDTO;
import com.dev.api.springrest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> listAll() {
        return ResponseEntity.ok(productService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(productService.findOneProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable long id, @RequestBody ProductDTO productDTO){
        productService.updateProduct(id, productDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<Void> createProduct(@RequestBody ProductDTO productDTO) throws Exception {
        productService.saveProduct(productDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
