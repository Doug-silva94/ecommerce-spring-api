package com.dev.api.springrest.service;

import com.dev.api.springrest.dto.ProductDto;
import com.dev.api.springrest.exception.ProductException;
import com.dev.api.springrest.model.Category;
import com.dev.api.springrest.model.Product;
import com.dev.api.springrest.repository.CategoryRepository;
import com.dev.api.springrest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ProductDto productToDTO(Product product) {
        Category category = new Category();
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setUnitaryValue(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setExpirationDate(product.getExpirationDate());
        productDto.setQuantity(product.getQuantity());
        productDto.setCatId(category.getId());

        return productDto;
    }

    public Product dtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getUnitaryValue());
        product.setDescription(productDto.getDescription());
        product.setExpirationDate(productDto.getExpirationDate());
        product.setQuantity(productDto.getQuantity());

        return product;
    }

    public void saveProduct(ProductDto productDto) throws Exception {
        Product product = dtoToProduct(productDto);
        product.setCategory(categoryRepository.findById(productDto.getCatId()).orElseThrow());
        productRepository.save(product);
    }

    public ProductDto findOneProduct(Long id) throws ProductException {
        var ex = new ProductException(new ProductException());
        return productToDTO(this.getProductOrElseThrow(id));
    }

    public Product getProductOrElseThrow(Long id) throws ProductException {
        return this.productRepository.findById(id).orElseThrow(ProductException::new);
    }

    public void updateProduct(Long id, ProductDto productDto) throws ProductException {
        Product productOnBank = this.getProductOrElseThrow(id);
        productOnBank.setDescription(productDto.getDescription());
        productOnBank.setExpirationDate(productDto.getExpirationDate());
        productOnBank.setQuantity(productDto.getQuantity());
        productRepository.save(productOnBank);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDto> listAll() {
        return productRepository.findAll()
                .stream()
                .map(this::productToDTO)
                .collect(Collectors.toList());
    }

}



