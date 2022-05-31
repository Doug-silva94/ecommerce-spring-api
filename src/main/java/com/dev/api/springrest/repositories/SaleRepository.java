package com.dev.api.springrest.repositories;

import com.dev.api.springrest.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
