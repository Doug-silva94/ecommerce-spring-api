package com.dev.api.springrest.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.api.springrest.dto.ReportDto;
import com.dev.api.springrest.dto.SaleDto;
import com.dev.api.springrest.exception.SaleException;
import com.dev.api.springrest.model.Client;
import com.dev.api.springrest.model.Product;
import com.dev.api.springrest.model.Sale;
import com.dev.api.springrest.repository.ClientRepository;
import com.dev.api.springrest.repository.ProductRepository;
import com.dev.api.springrest.repository.SaleRepository;

@Service
public class SaleService {

	@Autowired
	SaleRepository saleRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	ProductService productService;

	public SaleDto toDto(Sale sale) {
		SaleDto saleDto = new SaleDto();
		Product product = new Product();
		Client client = new Client();

		saleDto.setId(sale.getId());
		saleDto.setDate(sale.getDate());
		saleDto.setPrice(sale.getPrice());
		saleDto.setQuantity(sale.getQuantity());
		saleDto.setIdClient(client.getId());
		saleDto.setIdProd(product.getId());
		saleDto.setServiceType(sale.getServiceType());
		saleDto.setNfe(sale.getNfe());
		return saleDto;
	}

	public Sale toModel(SaleDto saleDto) {
		Sale sale = new Sale();
		sale.setDate(saleDto.getDate());
		sale.setPrice(saleDto.getPrice());
		sale.setQuantity(saleDto.getQuantity());
		sale.setServiceType(saleDto.getServiceType());
		sale.setNfe(saleDto.getNfe());
		return sale;
	}

	public void saveSale(SaleDto saleDto) throws MessagingException, SaleException {
		Sale sale = toModel(saleDto);
		Product product = productRepository.findById(saleDto.getIdProd()).get();
		Client client = clientRepository.findById(saleDto.getIdClient()).get();

		if (saleDto.getQuantity() <= product.getQuantity()) {
			product.setQuantity(product.getQuantity() - saleDto.getQuantity());

			sale.setProduct(productRepository.findById(saleDto.getIdProd()).orElseThrow());
			sale.setClient(clientRepository.findById(saleDto.getIdClient()).orElseThrow());
			saleRepository.save(sale);
			emailService.emailSale(product.getName(), saleDto.getQuantity(), product.getPrice(), client.getEmail());

			if (product.getQuantity() <= 5) {
				emailService.emailProductInventory(product.getName(), product.getQuantity());
			}
		}
		throw new SaleException();
	}

	public SaleDto findOneSale(Long id) throws SaleException {
		return saleRepository.findById(id).map(sale -> toDto(sale))
				.orElseThrow(() -> new SaleException("Category " + id + " not found. Please, try again!"));
	}

	public String updateSale(Long id, SaleDto saleDto) throws SaleException {
		Sale dataSale = this.saleRepository.findById(id)
				.orElseThrow(() -> new SaleException("Category " + id + " was not updated. Please, try again."));

		dataSale.setDate(saleDto.getDate());
		dataSale.setPrice(saleDto.getPrice());
		dataSale.setQuantity(saleDto.getQuantity());
		saleRepository.save(dataSale);
		return "Sale " + id + " successfully updated!";
	}

	public void deleteSale(long id) {
		saleRepository.deleteById(id);
	}

	public List<SaleDto> listAll() {
		return saleRepository.findAll().stream().map(sale -> toDto(sale)).collect(Collectors.toList());
	}

	public List<ReportDto> topFive() {
		return saleRepository.topFive();
	}

}