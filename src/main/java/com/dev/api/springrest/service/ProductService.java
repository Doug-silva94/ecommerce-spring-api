package com.dev.api.springrest.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.api.springrest.dto.ProductDto;
import com.dev.api.springrest.exception.ProductException;
import com.dev.api.springrest.model.Category;
import com.dev.api.springrest.model.Product;
import com.dev.api.springrest.repository.CategoryRepository;
import com.dev.api.springrest.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	EmailService emailService;

	public ProductDto toDto(Product product) {
		Category category = new Category();
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		productDto.setDescription(product.getDescription());
		productDto.setExpirationDate(product.getExpirationDate());
		productDto.setQuantity(product.getQuantity());
		productDto.setCatId(category.getId());
		return productDto;
	}

	public Product toModel(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setDescription(productDto.getDescription());
		product.setExpirationDate(productDto.getExpirationDate());
		product.setQuantity(productDto.getQuantity());
		return product;
	}

	public String saveProduct(ProductDto productDto) throws ProductException {
		Product product = toModel(productDto);
		product.setCategory(categoryRepository.findById(productDto.getCatId()).orElseThrow());
		productRepository.save(product);

		return "Product " + product.getId() + " successfully saved!";
	}

	public ProductDto findOneProduct(Long id) throws ProductException {
		return productRepository.findById(id).map(product -> toDto(product))
				.orElseThrow(() -> new ProductException("Product " + id + " not found. Please, try again!"));
	}

	public String updateProduct(Long id, ProductDto productDto) throws ProductException, MessagingException {
		Product dataProduct = this.productRepository.findById(id)
				.orElseThrow(() -> new ProductException("Category " + id + " was not updated. Please, try again."));

		dataProduct.setQuantity(productDto.getQuantity());

		if (productDto.getQuantity() <= 5) {
			emailService.emailProductInventory(dataProduct.getName(), dataProduct.getQuantity());
		}
		productRepository.save(dataProduct);
		return "Sale " + id + " successfully updated!";
	}

	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}

	public List<ProductDto> listAll() {
		return productRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

}
