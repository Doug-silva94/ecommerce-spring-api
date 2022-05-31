package com.dev.api.springrest.services;

import com.dev.api.springrest.dtos.SaleDTO;
import com.dev.api.springrest.exceptions.SaleException;
import com.dev.api.springrest.models.Client;
import com.dev.api.springrest.models.Product;
import com.dev.api.springrest.models.Sale;
import com.dev.api.springrest.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SaleService {
    @Autowired
    SaleRepository saleRepository;

    @Autowired


    public void buyProduct(SaleDTO saleDTO) {
        Sale sale = dtoToSale(saleDTO);
        saleRepository.save(sale);
    }

    public SaleDTO saleToDTO(Sale sale, Product product, Client client) {
        SaleDTO saleDTO = new SaleDTO();

        saleDTO.setId(sale.getId());
        saleDTO.setDate(sale.getDate());
        saleDTO.setPrice(sale.getPrice());
        saleDTO.setQuantity(sale.getQuantity());
        saleDTO.setIdClient(client.getId());
        saleDTO.setIdProd(product.getId());
        return saleDTO;
    }

//              Verificar Exception
//    public Sale getSaleOrElseThrow(Long id) throws SaleNotFoundException {
//        return this.saleRepository.findById(id).orElseThrow(SaleNotFoundException::new);
//    }

    private <T> T getValue(T savedData, String dtoInput) {
        return dtoInput != null ? (T) dtoInput : savedData;
    }

    public Sale dtoToSale(SaleDTO saleDTO) {
        Sale sale = new Sale();
        sale.setId(saleDTO.getId());
        sale.setDate(saleDTO.getDate());
        sale.setPrice(saleDTO.getPrice());
        sale.setQuantity(saleDTO.getQuantity());
        return sale;
    }

    public Optional<Sale> findOneSale(Long id) {
//        var ex = new SaleException(new SaleNotFoundException());
        return saleRepository.findById(id);
    }

    public String updateSale(Long id, SaleDTO saleDTO) throws SaleException {
        Optional <Sale> sale = saleRepository.findById(id);
        Sale dataSale = new Sale();
        
        if (sale.isPresent()) {
        	dataSale = sale.get();   
        	if (saleDTO.getDate() != null) {
        	dataSale.setDate(saleDTO.getDate());
        	}
        	if (saleDTO.getQuantity() != null) {
        	saleDTO.setQuantity(saleDTO.getQuantity());
        	}
        	if (saleDTO.getPrice() != null) {
        	saleDTO.setPrice(saleDTO.getPrice());
        }
        saleRepository.save(saleDTO);
        return "Sale " + dataSale.getId() + " successfully updated!";
    }
    throw new SaleException("Sale " + dataSale.getId() + " was not updated. Please, try again.");
    }

    public void deleteSale(long id){
        saleRepository.deleteById(id);
    }

//    public List<SaleDTO> listAll() {
//        return saleRepository.findAll()
//                .stream()
//                .map(this::saleToDTO)
//                .collect(Collectors.toList());
//    }
    
}
