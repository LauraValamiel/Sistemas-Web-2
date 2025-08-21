package br.edu.ufop.web.ticket.sales.converter;

import java.util.Collections;
import java.util.stream.Collectors;

import br.edu.ufop.web.ticket.sales.domain.EventDomain;
import br.edu.ufop.web.ticket.sales.dtos.CreateEventDTO;
import br.edu.ufop.web.ticket.sales.dtos.EventDTO;
import br.edu.ufop.web.ticket.sales.dtos.UpdateEventDTO;
import br.edu.ufop.web.ticket.sales.models.EventModel;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EventConverter {

    public static EventDTO toEventDTO(EventModel eventModel) {
        if (eventModel == null) {
            return null;
        }

        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventModel.getId());
        eventDTO.setType(eventModel.getType());
        eventDTO.setDescription(eventModel.getDescription());
        eventDTO.setDate(eventModel.getDate());
        eventDTO.setStartSales(eventModel.getStartSales());
        eventDTO.setEndSales(eventModel.getEndSales());
        eventDTO.setPrice(eventModel.getPrice());
        eventDTO.setCreatedAt(eventModel.getCreatedAt());
        eventDTO.setUpdatedAt(eventModel.getUpdatedAt());

        return eventDTO;

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
