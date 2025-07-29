package br.edu.ufop.web.ticket.sales.converter;


import br.edu.ufop.web.ticket.sales.domain.SaleDomain;
import br.edu.ufop.web.ticket.sales.dtos.CreateSaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.SaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.UpdateSaleDTO;
import br.edu.ufop.web.ticket.sales.models.SaleModel;

public class SaleConverter {

    public static SaleDTO toSaleDTO(SaleModel saleModel) {
        return new SaleDTO(
            saleModel.getId(),
            saleModel.getUserId(),
            saleModel.getSaleDate(),
            saleModel.getSaleStatus(),
            saleModel.getCreatedAt(),
            saleModel.getUpdatedAt(),
            saleModel.getEventId()
        );
    }

    public static SaleModel toSaleModel(SaleDomain saleDomain){
        return SaleModel.builder()
        .id(saleDomain.getId())
        .userId(saleDomain.getUserId())
        .saleDate(saleDomain.getSaleDate())
        .saleStatus(saleDomain.getSaleStatus())
        .createdAt(saleDomain.getCreatedAt())
        .updatedAt(saleDomain.getUpdatedAt())
        .eventId(saleDomain.getEventId())
        .build();

    }

    public static SaleDomain toSaleDomain(CreateSaleDTO createSaleDTO) {
        return SaleDomain.builder()
        .userId(createSaleDTO.getUserId())
        .saleStatus(createSaleDTO.getSaleStatus())
        .build();

    }

    public static SaleDomain toSaleDomain(UpdateSaleDTO updateSaleDTO) {
        return SaleDomain.builder()
            .id(updateSaleDTO.getId())
            .saleStatus(updateSaleDTO.getSaleStatus())
            .build();
    }

 

}
