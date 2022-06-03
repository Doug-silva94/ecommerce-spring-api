package com.dev.api.springrest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public SaleDto saleToDto(Sale sale) {
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


    public Sale getSaleOrElseThrow(Long id) throws SaleException {
        return this.saleRepository.findById(id).orElseThrow(SaleException::new);
    }

    private <T> T getValue(T savedData, String dtoInput) {
        return dtoInput != null ? (T) dtoInput : savedData;
    }

    public Sale dtoToSale(SaleDto saleDto) {
        Sale sale = new Sale();
        sale.setId(saleDto.getId());
        sale.setDate(saleDto.getDate());
        sale.setPrice(saleDto.getPrice());
        sale.setQuantity(saleDto.getQuantity());
        sale.setServiceType(saleDto.getServiceType());
        sale.setNfe(saleDto.getNfe());
        return sale;
    }

    public Optional<Sale> findOneSale(Long id) {
        var ex = new SaleException(new SaleException());
        return saleRepository.findById(id);
    }

    public SaleDto findOneById(Long id) throws SaleException {
        var exc = new SaleException(new SaleException());
        return saleToDto(this.getSaleOrElseThrow(id));
    }

    public void saveSale(SaleDto saleDto) {
        Sale sale = dtoToSale(saleDto);
        sale.setProduct(productRepository.findById(saleDto.getIdProd()).orElseThrow());
        sale.setClient(clientRepository.findById(saleDto.getIdClient()).orElseThrow());
        saleRepository.save(sale);
    }

    public void updateSale(Long id, SaleDto saleDto) {
        Sale sale = saleRepository.findById(id).orElseThrow();
        if (saleDto.getPrice() != null) {
            sale.setPrice(saleDto.getPrice());
        }
        if (saleDto.getQuantity() != null) {
            sale.setQuantity(saleDto.getQuantity());
        }
        saleRepository.save(sale);
    }


    public void deleteSale(long id) {
        saleRepository.deleteById(id);
    }

    public List<SaleDto> listAll() {
        return saleRepository.findAll()
                .stream()
                .map(sale -> saleToDto(sale))
                .collect(Collectors.toList());
     }
    
    public List<ReportDto> topFive(){
    	return saleRepository.topFive();
  
    	
    	 
    }
    

}