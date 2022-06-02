package com.dev.api.springrest.controller;

import com.dev.api.springrest.dto.ProductSaleDto;
import com.dev.api.springrest.exception.ProductSaleException;
import com.dev.api.springrest.service.ProductSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProductSaleController {

    @Autowired
    ProductSaleService productSaleService;

    @PostMapping()
    public ResponseEntity<Void> createProduct(@RequestBody ProductSaleDto productSaleDto) throws Exception {
        productSaleService.saveSale(productSaleDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductSaleDto> findById(@PathVariable long id) throws ProductSaleException {
        return ResponseEntity.ok(productSaleService.findOneProductSale(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        productSaleService.deleteSale(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<List<ProductSaleDto>> listAll() {
        return ResponseEntity.ok(productSaleService.listAll());
    }

}