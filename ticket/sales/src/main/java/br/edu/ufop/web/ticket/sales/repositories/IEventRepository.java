package br.edu.ufop.web.ticket.sales.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ufop.web.ticket.sales.enums.EnumEventType;
import br.edu.ufop.web.ticket.sales.models.EventModel;

public interface IEventRepository extends JpaRepository<EventModel, UUID> {

    List<EventModel> findByType(EnumEventType type);
    List<EventModel> findByDescriptionContainingIgnoreCase(String description);
    List<EventModel> findByDate(LocalDateTime date);
    @Query("SELECT e FROM EventModel e LEFT JOIN FETCH e.sales")
    List<EventModel> findAllWithSales();

}
