package com.dev.api.springrest.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.api.springrest.dtos.CategoryDto;
import com.dev.api.springrest.dtos.ProductDTO;
import com.dev.api.springrest.models.Category;
import com.dev.api.springrest.models.Product;
import com.dev.api.springrest.models.ProductionSale;

@Service
public class ProductionSaleService {

	@Autowired
	ProductionSaleRepository productionSaleRepository;

	public ProductionSaleDto ProductionSaleToDto(ProductionSale productionSale) {
		ProductionSaleDto productionSaleDto = new ProductionSaleDto();
		productionSaleDto.setIdRel(productionSale.getIdRel());
		productionSaleDto.setIdProd(productionSale.getIdProd());
		productionSaleDto.setIdSale(productionSale.getIdSale());
		return productionSaleDto;
	}

	public ProductionSale dtoToProductionSale(ProductionSaleDto productionSaleToDto) {
		ProductionSale productionSale = new ProductionSale();
		productionSale.setIdProd(productionSaleToDto.getIdProd());
		productionSale.setIdSale(productionSaleToDto.getIdSale());
		return productionSale;
	}

	public void saveProductionSale(ProductionSaleDto productionSaleDto) /* throws Exception */ {
		ProductionSale productionSale = dtoToProductionSale(productionSaleDto);
		// productionSale.setCategory(categoryRepository.findById(productDTO.getCatId()).orElseThrow());
		productionSaleRepository.save(productionSale);
	}

	public ProductionSaleDto findOneProductionSale(Long id) {
		Optional<ProductionSale> productionSale = productionSaleRepository.findById(id);
		ProductionSale productionSaleOnData;
		ProductionSaleDto productionSaleDto = new ProductionSaleDto();
		if (productionSaleDto.isPresent()) {
			productionSaleOnData = productionSale.get();
			productionSaleDto = categoryToDto(productionSale.get());
		}
		return productionSaleDto;
	}

	public void updateCategory(Long id, ProductionSaleDto productionSaleDto) {
		ProductionSale productionSale = productionSaleRepository.findById(id).orElseThrow();

		if (productionSaleDto.getIdRel() != null) {
			productionSale.setIdRel(productionSaleDto.getIdRel());
		}
		if (productionSaleDto.getIdProd() != null) {
			productionSale.setIdProd(productionSaleDto.getDgetIdProdescription());
		}
		if (productionSaleDto.getIdSale() != null) {
			productionSale.setIdSale(productionSaleDto.getIdSale());
		}
		productionSaleDto.setIdSale(productionSale.getIdSale());
		productionSaleRepository.save(productionSale);
	}

	public void deleteProductionSale(long id) {
		productionSaleRepository.deleteById(id);
	}

	public List<ProductionSaleDto> listAll() {
		return productionSaleRepository.findAll()
				.stream()
				.map(this::ProductionSaleToDto)
				.collect(Collectors.toList());
	}

}
