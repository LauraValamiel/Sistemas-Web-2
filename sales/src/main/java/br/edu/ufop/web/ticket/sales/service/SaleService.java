package br.edu.ufop.web.ticket.sales.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import java.util.Optional;

import org.springframework.stereotype.Service;
//import org.yaml.snakeyaml.events.Event;

import br.edu.ufop.web.ticket.sales.converter.SaleConverter;
import br.edu.ufop.web.ticket.sales.domain.SaleDomain;
import br.edu.ufop.web.ticket.sales.dtos.CreateSaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.DeleteSaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.SaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.UpdateSaleDTO;
import br.edu.ufop.web.ticket.sales.enums.EnumSalesType;
import br.edu.ufop.web.ticket.sales.models.EventModel;
import br.edu.ufop.web.ticket.sales.models.SaleModel;
import br.edu.ufop.web.ticket.sales.repositories.IEventRepository;
import br.edu.ufop.web.ticket.sales.repositories.ISaleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaleService {

    private final ISaleRepository saleRepository;

    private final IEventRepository eventRepository;

    public List<SaleDTO> getAllSales() {
        
        List<SaleModel> saleModelList = saleRepository.findAll();

        return saleModelList
            .stream()
            .map(SaleConverter::toSaleDTO)
            .toList();

    }

    public SaleDTO getSaleById(String saleId) {
        
        UUID uuid = UUID.fromString(saleId);
        Optional<SaleModel> optionalSaleModel = saleRepository.findById(uuid);
        if (optionalSaleModel.isPresent()){
            SaleModel saleModel = optionalSaleModel.get();
            return SaleConverter.toSaleDTO(saleModel);
        }

        return null;
    }
    
    public SaleDTO createSale(CreateSaleDTO createSaleDTO) {

        EventModel eventModel = eventRepository.findById(createSaleDTO.getEventId())
            .orElseThrow(() -> new RuntimeException("Event not found"));

        SaleDomain saleDomain = SaleConverter.toSaleDomain(createSaleDTO);

        saleDomain.setEventId(eventModel);
        saleDomain.setSaleDate(LocalDateTime.now());
        if (saleDomain.getSaleStatus() == null) {
            saleDomain.setSaleStatus(EnumSalesType.EM_ABERTO);
        }

        SaleModel saleModel = SaleConverter.toSaleModel(saleDomain);
        
        return SaleConverter.toSaleDTO(saleRepository.save(saleModel));
    }

    public SaleDTO updateSale(UpdateSaleDTO updateSaleDTO) {

        Optional<SaleModel> optionalSaleModel = saleRepository.findById(updateSaleDTO.getId());

        if (!optionalSaleModel.isPresent()) {
            return null;
        }

        SaleModel saleModel = optionalSaleModel.get();

        saleModel.setSaleStatus(updateSaleDTO.getSaleStatus());

        return SaleConverter.toSaleDTO(saleRepository.save(saleModel));
    }

    public void deleteSale(DeleteSaleDTO deleteSaleDTO) {
        
        Optional<SaleModel> optionalSaleModel = saleRepository.findById(deleteSaleDTO.id());
        
        if (optionalSaleModel.isEmpty()) {
            throw new RuntimeException("Sale not found");
        }   

        saleRepository.delete(optionalSaleModel.get());
    }

}
