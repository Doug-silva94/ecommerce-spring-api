package com.dev.api.springrest.repository;

import com.dev.api.springrest.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
