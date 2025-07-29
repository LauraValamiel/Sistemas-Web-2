package br.edu.ufop.web.ticket.sales.converter;

import br.edu.ufop.web.ticket.sales.domain.EventDomain;
import br.edu.ufop.web.ticket.sales.dtos.CreateEventDTO;
import br.edu.ufop.web.ticket.sales.dtos.EventDTO;
import br.edu.ufop.web.ticket.sales.dtos.UpdateEventDTO;
import br.edu.ufop.web.ticket.sales.models.EventModel;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EventConverter {

    public static EventDTO toEventDTO(EventModel eventModel) {
        return new EventDTO(
            eventModel.getId(),
            eventModel.getType(),
            eventModel.getDescription(),
            eventModel.getDate(),
            eventModel.getStartSales(),
            eventModel.getEndSales(),
            eventModel.getPrice(),
            eventModel.getCreatedAt(),
            eventModel.getUpdatedAt()
        );

    }

    public static EventModel toEventModel(EventDomain eventDomain) {
        return EventModel.builder()
        .type(eventDomain.getType())
        .description(eventDomain.getDescription())
        .date(eventDomain.getDate())
        .startSales(eventDomain.getStartSales())
        .endSales(eventDomain.getEndSales())
        .price(eventDomain.getPrice())
        .createdAt(eventDomain.getCreatedAt())
        .updatedAt(eventDomain.getUpdatedAt())
        .build();
    }


    public static EventDomain toEventDomain(CreateEventDTO createEventDTO) {
        return EventDomain.builder()
        .type(createEventDTO.getType())
        .description(createEventDTO.getDescription())
        .date(createEventDTO.getDate())
        .startSales(createEventDTO.getStartSales())
        .endSales(createEventDTO.getEndSales())
        .price(createEventDTO.getPrice())
        .createdAt(createEventDTO.getCreatedAt())
        .updatedAt(createEventDTO.getUpdatedAt())
        .build();

    }

    public static EventDomain toEventDomain(UpdateEventDTO updateEventDTO) {
        return EventDomain.builder()
            .description(updateEventDTO.getDescription())
            .date(updateEventDTO.getDate())
            .startSales(updateEventDTO.getStartSales())
            .endSales(updateEventDTO.getEndSales())
            .price(updateEventDTO.getPrice())
            .updatedAt(updateEventDTO.getUpdatedAt())
            .build();
    }

}
