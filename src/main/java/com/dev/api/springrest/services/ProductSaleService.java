package com.dev.api.springrest.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dev.api.springrest.dtos.ProductSaleDto;
import com.dev.api.springrest.exceptions.ProductSaleException;
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

	public void saveSale(ProductSaleDto productSaleDto) {
		ProductSale productSale = dtoToProductSale(productSaleDto);
		// productSale.setCategory(categoryRepository.findById(productDTO.getCatId()).orElseThrow());
		productSaleRepository.save(productSale);
	}

	public ProductSaleDto findOneProductSale(Long id) throws ProductSaleException {
		Optional<ProductSale> productSale = productSaleRepository.findById(id);
		ProductSale dataProductSale = new ProductSale();
		ProductSaleDto productSaleDTO = new ProductSaleDto();
		
		if (productSale.isPresent()) {
			dataProductSale = productSale.get();
			productSaleDTO = productSaleToDto(productSale.get());
			return productSaleDTO;
		}
		throw new ProductSaleException("Product Sale " + dataProductSale.getIdProd() + " not found. Please, try again.");
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
