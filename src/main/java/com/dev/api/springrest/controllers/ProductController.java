package com.dev.api.springrest.controllers;

import com.dev.api.springrest.dtos.ProductDto;
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

    @GetMapping()
    public ResponseEntity<List<ProductDto>> listAll() {
        return ResponseEntity.ok(productService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable long id){
        return ResponseEntity.ok(productService.findOneProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable long id, @RequestBody ProductDto productDTO){
        productService.updateProduct(id, productDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDTO) throws Exception {
        productService.saveProduct(productDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    @GetMapping("/filter")
//    public List<ProductDto> findProduct(@RequestParam("name") String name){
//        return this.productRepository.findByProducts(name)
//                        .stream()
//                                .map(productService::productToDTO)
//                                        .collect(Collectors.toList());
//
//    }

}
