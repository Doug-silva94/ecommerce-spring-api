package com.dev.api.springrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.api.springrest.dtos.ProductDTO;
import com.dev.api.springrest.services.ProductService;

@Controller
@RequestMapping("productSale")
public class ProductSaleController {
	
	 public ProductSaleController(ProductSaleService productSaleService) {
	        this.productSaleService = productSaleService;
	    }

	    @Autowired
	    ProductSaleService productSaleService;

	    @GetMapping()
	    public ResponseEntity<List<ProductSaleDto>> listAll() {
	        return ResponseEntity.ok(productSaleService.listAll());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ProductSaleDto> findById(@PathVariable long id){
	        return ResponseEntity.ok(productSaleService.findOneProductSale(id));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Void> updateById(@PathVariable long id, @RequestBody ProductSaleDto productSaleDto){
	        productSaleService.updateProductSale(id, productSaleDto);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }

	    @PostMapping()
	    public ResponseEntity<Void> createProduct(@RequestBody ProductSaleDto productSaleDto) throws Exception {
	        productSaleService.saveProduct(productSaleDto);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteById(@PathVariable long id){
	        productSaleService.deleteProductSale(id);
	        return new ResponseEntity<>(HttpStatus.ACCEPTED);
	    }

	}
