package br.edu.ufop.web.ticket.sales.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ufop.web.ticket.sales.enums.EnumSalesType;
import br.edu.ufop.web.ticket.sales.models.EventModel;
import br.edu.ufop.web.ticket.sales.models.SaleModel;


public interface ISaleRepository extends JpaRepository<SaleModel, UUID> {

    // Define methods for querying sales data as needed
    List<SaleModel> findBySaleStatus(EnumSalesType saleStatus);
    List<SaleModel> findByEventId(EventModel event);
    List<SaleModel> findByUserId(UUID user);
    List<SaleModel> findBySaleDate(LocalDateTime saleDate);

}
