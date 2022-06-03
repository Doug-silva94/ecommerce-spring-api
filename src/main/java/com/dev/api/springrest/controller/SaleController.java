package com.dev.api.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.api.springrest.dto.ReportDto;
import com.dev.api.springrest.dto.SaleDto;
import com.dev.api.springrest.exception.SaleException;
import com.dev.api.springrest.service.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    SaleService saleService;

    @PostMapping()
    public ResponseEntity<Void> createProduct(@RequestBody SaleDto saleDto) throws Exception {
        saleService.saveSale(saleDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDto> findById(@PathVariable long id) throws SaleException {
        return ResponseEntity.ok(saleService.findOneById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        saleService.deleteSale(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<List<SaleDto>> listAll() {
        return ResponseEntity.ok(saleService.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable long id, @RequestBody SaleDto saleDto) throws SaleException {
        saleService.updateSale(id, saleDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @GetMapping("/topFive")
    public ResponseEntity<List<ReportDto>> topFive() {
        return ResponseEntity.ok(saleService.topFive());
    }
}