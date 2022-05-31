package com.dev.api.springrest.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dev.api.springrest.dtos.ProductSaleDto;
import com.dev.api.springrest.models.ProductSale;
import com.dev.api.springrest.repositories.ProductSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductSaleService {

	@Autowired
	ProductSaleRepository productSaleRepository;

	public ProductSaleDto productSaleToDto(ProductSale productSale) {
		ProductSaleDto productSaleDto = new ProductSaleDto();
		productSaleDto.setId(productSale.getIdRel());
		productSaleDto.setIdProd(productSale.getIdProd());
		productSaleDto.setIdSale(productSale.getIdSale());
		return productSaleDto;
	}

	public ProductSale dtoToProductSale(ProductSaleDto productSaleToDto) {
		ProductSale productSale = new ProductSale();
		productSale.setIdProd(productSaleToDto.getIdProd());
		productSale.setIdSale(productSaleToDto.getIdSale());
		return productSale;
	}

	public void saveSale(ProductSaleDto productSaleDto) /* throws Exception */ {
		ProductSale productSale = dtoToProductSale(productSaleDto);
		// productSale.setCategory(categoryRepository.findById(productDTO.getCatId()).orElseThrow());
		productSaleRepository.save(productSale);
	}

	public ProductSaleDto findOneProductSale(Long id) {
		Optional<ProductSale> productSale = productSaleRepository.findById(id);
		ProductSale productSaleOnData;
		ProductSaleDto productSaleDto = new ProductSaleDto();
		if (productSale.isPresent()) {
			productSaleOnData = productSale.get();
			productSaleDto = productSaleToDto(productSale.get());
		}
		return productSaleDto;
	}


	public void deleteSale(long id) {
		productSaleRepository.deleteById(id);
	}

	public List<ProductSaleDto> listAll() {
		return productSaleRepository.findAll()
				.stream()
				.map(this::productSaleToDto)
				.collect(Collectors.toList());
	}

}
