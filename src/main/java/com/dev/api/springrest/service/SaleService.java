package com.dev.api.springrest.service;

import com.dev.api.springrest.dto.SaleDto;
import com.dev.api.springrest.exception.SaleException;
import com.dev.api.springrest.model.Client;
import com.dev.api.springrest.model.Product;
import com.dev.api.springrest.model.Sale;
import com.dev.api.springrest.repository.ClientRepository;
import com.dev.api.springrest.repository.ProductRepository;
import com.dev.api.springrest.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

//    public Optional<Sale> findOneSale(Long id) {
//        var ex = new SaleException(new SaleException());
//        return saleRepository.findById(id);
//    }

    public SaleDto findById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        Sale dataSale = new Sale();
        SaleDto saleDto = new SaleDto();

        if(sale.isPresent()) {
            dataSale = sale.get();
            saleToDto(dataSale);
        }
            return saleDto;
    }

//    public void saveSale(SaleDto saleDto) {
//        Sale sale = dtoToSale(saleDto);
//        sale.setProducts(productRepository.findById(saleDto.getIdProd()).orElseThrow());
//        saleRepository.save(sale);
//    }

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
        return (List<SaleDto>) saleRepository.findAll().spliterator();
    }

}