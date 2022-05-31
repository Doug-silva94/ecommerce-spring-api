package com.dev.api.springrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.api.springrest.models.ProductionSale;

public interface ProductionSaleRepository extends JpaRepository<ProductionSale, Long> {

	
}
