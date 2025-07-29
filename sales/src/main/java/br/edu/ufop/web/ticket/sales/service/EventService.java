package br.edu.ufop.web.ticket.sales.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.edu.ufop.web.ticket.sales.converter.EventConverter;
import br.edu.ufop.web.ticket.sales.domain.EventDomain;
import br.edu.ufop.web.ticket.sales.dtos.CreateEventDTO;
import br.edu.ufop.web.ticket.sales.dtos.DeleteEventDTO;
import br.edu.ufop.web.ticket.sales.dtos.EventDTO;
import br.edu.ufop.web.ticket.sales.dtos.UpdateEventDTO;
import br.edu.ufop.web.ticket.sales.models.EventModel;
import br.edu.ufop.web.ticket.sales.repositories.IEventRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventService {

    private final IEventRepository eventRepository;

    public List<EventDTO> getAllEvents() {

        List<EventModel> eventModelList = eventRepository.findAll();

        return eventModelList
            .stream()
            .map(EventConverter::toEventDTO)
            .toList();
    }

    public EventDTO getEventById(String eventId) {

        UUID uuid = UUID.fromString(eventId);
        Optional<EventModel> optionalEventModel = eventRepository.findById(uuid);
        if (optionalEventModel.isPresent()){
            EventModel eventModel = optionalEventModel.get();
            return EventConverter.toEventDTO(eventModel);
        }

        return null;
    }


    public EventDTO createEvent(CreateEventDTO createEventDTO) {

        EventDomain eventDomain = EventConverter.toEventDomain(createEventDTO);

        EventModel eventModel = EventConverter.toEventModel(eventDomain);
        
        return EventConverter.toEventDTO(eventRepository.save(eventModel));
    }


    public EventDTO updateEvent(UpdateEventDTO updateEventDTO){

        //EventDomain eventDomain = EventConverter.toEventDomain(updateEventDTO);

        Optional<EventModel> optionalEventModel = eventRepository.findById(updateEventDTO.getId());

        if(optionalEventModel.isEmpty()) {
            return null;
        }

        EventModel eventModel = optionalEventModel.get();
        EventDomain eventDomain = EventConverter.toEventDomain(updateEventDTO);

        eventModel.setDescription(eventDomain.getDescription());
        eventModel.setDate(eventDomain.getDate());
        eventModel.setStartSales(eventDomain.getStartSales());
        eventModel.setEndSales(eventDomain.getEndSales());
        eventModel.setPrice(eventDomain.getPrice());

        return EventConverter.toEventDTO(eventRepository.save(eventModel));

    }

    public void deleteEvent(DeleteEventDTO deleteEventDTO){

        Optional<EventModel> optionalEventModel = eventRepository.findById(deleteEventDTO.id());

        if (optionalEventModel.isEmpty()){
            throw new RuntimeException("Event not found");
        }

        eventRepository.delete(optionalEventModel.get());
    }

    

}
