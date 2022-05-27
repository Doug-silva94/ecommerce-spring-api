package com.dev.api.springrest.repositories;

import com.dev.api.springrest.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{


//    @Query(value="select * from product order by prod_id DESC")
//    List<Product> findAll();
    @Query(value="select P from Product P Order By prod_id DESC")
    List<Product> findAllHql();
//    List<Product> findValueProd(double unitaryValue);

}