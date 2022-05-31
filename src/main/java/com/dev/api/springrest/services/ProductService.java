package com.dev.api.springrest.services;

import com.dev.api.springrest.dtos.ProductDTO;
import com.dev.api.springrest.models.Product;
import com.dev.api.springrest.repositories.CategoryRepository;
import com.dev.api.springrest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public void saveProduct(ProductDTO productDTO) throws Exception {
		Product product = dtoToProduct(productDTO);
		product.setCategory(categoryRepository.findById(productDTO.getCatId()).orElseThrow());
		productRepository.save(product);
	}

	public ProductDTO productToDTO(Product product){
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setUnitaryValue(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setExpirationDate(product.getExpirationDate());
		productDTO.setQuantity(product.getQuantity());
		productDTO.setCatId(product.getId());

		return productDTO;
	}

	public Product dtoToProduct(ProductDTO productDTO){
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getUnitaryValue());
		product.setDescription(productDTO.getDescription());
		product.setExpirationDate(productDTO.getExpirationDate());
		product.setQuantity(productDTO.getQuantity());
		return product;
	}

	public void deleteProduct(long id){productRepository.deleteById(id);}

	public List<ProductDTO> listAll() {
		List<Product> product = productRepository.findAll();
		List<ProductDTO> listDTO = new ArrayList<>();
		for (Product products : product ){
			ProductDTO productDTO=  productToDTO(products);
			listDTO.add(productDTO);
		}
		return listDTO;
	}


	public ProductDTO findOneProduct(Long id){
		Optional<Product> product = productRepository.findById(id);
		Product productOnData;
		ProductDTO productDTO = new ProductDTO();
		if (product.isPresent()){
			productOnData = product.get();
			productToDTO(product.get());
		}
		return productDTO;
	}

	public void updateProduct(Long id, ProductDTO productDTO) {
		Optional<Product> product = productRepository.findById(id);
		Product productOnBank = new Product();
		if (product.isPresent()) {
			productOnBank = product.get();
			if (productDTO.getName() != null) {
				productOnBank.setPrice(productDTO.getUnitaryValue());
			}
			if (productDTO.getDescription() != null) {
				productOnBank.setDescription(productDTO.getDescription());
			}
			if (productDTO.getExpirationDate() != null) {
				productOnBank.setExpirationDate(productDTO.getExpirationDate());
			}
			if (productDTO.getQuantity() != 0) {
				productOnBank.setQuantity(productDTO.getQuantity());
			}
			productRepository.save(productOnBank);
		}
	}
}



