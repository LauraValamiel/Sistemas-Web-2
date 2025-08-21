package br.edu.ufop.web.ticket.sales.converter;


import br.edu.ufop.web.ticket.sales.domain.SaleDomain;
import br.edu.ufop.web.ticket.sales.dtos.CreateSaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.SaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.UpdateSaleDTO;
import br.edu.ufop.web.ticket.sales.models.SaleModel;
import br.edu.ufop.web.ticket.sales.models.EventModel;
import br.edu.ufop.web.ticket.sales.dtos.externals.users.UserDTO;
import br.edu.ufop.web.ticket.sales.enums.EnumSalesType;

public class SaleConverter {

    public static SaleDTO toSaleDTO(SaleModel saleModel, UserDTO userDTO) {
        if (saleModel == null) {
            return null;
        }

        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setId(saleModel.getId());
        saleDTO.setUserId(saleModel.getUserId());
        saleDTO.setSaleDate(saleModel.getSaleDate());
        saleDTO.setSaleStatus(saleModel.getSaleStatus());
        saleDTO.setCreatedAt(saleModel.getCreatedAt());
        saleDTO.setUpdatedAt(saleModel.getUpdatedAt());

        if (saleModel.getEvent() != null){
            saleDTO.setEvent(EventConverter.toEventDTO(saleModel.getEvent()));
        }

        saleDTO.setUser(userDTO);

        return saleDTO;
      
    }

    public static SaleModel toSaleModel(SaleDomain saleDomain, EventModel eventModel){
        return SaleModel.builder()
        .id(saleDomain.getId())
        .userId(saleDomain.getUserId())
        .saleDate(saleDomain.getSaleDate())
        .saleStatus(saleDomain.getSaleStatus())
        .createdAt(saleDomain.getCreatedAt())
        .updatedAt(saleDomain.getUpdatedAt())
        .event(eventModel)
        .build();

    }

    public static SaleDomain toSaleDomain(CreateSaleDTO createSaleDTO) {
        return SaleDomain.builder()
            .userId(createSaleDTO.getUserId())
            .saleStatus(
                createSaleDTO.getSaleStatus() != null
                    ? EnumSalesType.fromId(createSaleDTO.getSaleStatus())
                    : EnumSalesType.EM_ABERTO
            )
            .build();

    }

    public static SaleDomain toSaleDomain(UpdateSaleDTO updateSaleDTO) {
        return SaleDomain.builder()
            .id(updateSaleDTO.getId())
            .saleStatus(EnumSalesType.fromCode(updateSaleDTO.getSaleStatus()))
            .build();
    }

 

}
