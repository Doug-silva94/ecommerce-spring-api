package com.dev.api.springrest.services;

import com.dev.api.springrest.dtos.CategoryDto;
import com.dev.api.springrest.dtos.ProductDto;
import com.dev.api.springrest.exceptions.CategoryException;
import com.dev.api.springrest.exceptions.ProductException;
import com.dev.api.springrest.models.Category;
import com.dev.api.springrest.models.Client;
import com.dev.api.springrest.models.Product;
import com.dev.api.springrest.repositories.CategoryRepository;
import com.dev.api.springrest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public ProductDto productToDTO(Product product){
		Category category = new Category();
		ProductDto productDTO = new ProductDto();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setUnitaryValue(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setExpirationDate(product.getExpirationDate());
		productDTO.setQuantity(product.getQuantity());
		productDTO.setCatId(category.getId());

		return productDTO;
	}

	public Product dtoToProduct(ProductDto productDTO){
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getUnitaryValue());
		product.setDescription(productDTO.getDescription());
		product.setExpirationDate(productDTO.getExpirationDate());
		product.setQuantity(productDTO.getQuantity());

		return product;
	}

	public void deleteProduct(long id){productRepository.deleteById(id);}

	public void saveProduct(ProductDto productDTO) throws Exception {
		Product product = dtoToProduct(productDTO);
		product.setCategory(categoryRepository.findById(productDTO.getCatId()).orElseThrow());
		productRepository.save(product);
	}

	public Product getProductOrElseThrow(Long id) throws ProductException {
		return this.productRepository.findById(id).orElseThrow(ProductException::new);
	}
	public ProductDto findOneProduct(Long id) throws ProductException {
		var ex = new ProductException(new ProductException());
		return productToDTO(this.getProductOrElseThrow(id));
	}


	public List<ProductDto> listAll() {
		return productRepository.findAll()
				.stream()
				.map(this::productToDTO)
				.collect(Collectors.toList());
	}


	public void updateProduct(Long id, ProductDto productDTO) throws ProductException {
		Product productOnBank = this.getProductOrElseThrow(id);
		productOnBank.setDescription(productDTO.getDescription());
		productOnBank.setExpirationDate(productDTO.getExpirationDate());
		productOnBank.setQuantity(productDTO.getQuantity());
		productRepository.save(productOnBank);
	}
}



