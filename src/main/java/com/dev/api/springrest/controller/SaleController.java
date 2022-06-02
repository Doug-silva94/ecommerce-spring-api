package com.dev.api.springrest.controller;

import com.dev.api.springrest.dto.SaleDto;
import com.dev.api.springrest.exception.SaleException;
import com.dev.api.springrest.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    SaleService saleService;

//    @PostMapping()
//    public ResponseEntity<Void> createProduct(@RequestBody SaleDto saleDto) throws Exception {
//        saleService.saveSale(saleDto);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDto> findById(@PathVariable long id) throws SaleException {
        return ResponseEntity.ok(saleService.findById(id));
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

}