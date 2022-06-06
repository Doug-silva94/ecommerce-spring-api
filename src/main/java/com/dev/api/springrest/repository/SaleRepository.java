package com.dev.api.springrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.api.springrest.dto.ReportDto;
import com.dev.api.springrest.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query(value = "select sale.prod_id as idProduto, sum(sale.quantity) as quantidade, sum(sale.price) as valor \r\n"
			+ "from sale \r\n"
			+ "where sale.service_type = 'Sale'  \r\n"
			+ "group by sale.prod_id \r\n"
			+ "order by sum(sale.quantity)\r\n"
			+ "desc limit 5", nativeQuery = true)
	List<ReportDto> topFive();
	
	
}
