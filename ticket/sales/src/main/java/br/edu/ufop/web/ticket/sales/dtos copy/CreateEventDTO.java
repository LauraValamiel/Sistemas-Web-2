package br.edu.ufop.web.ticket.sales.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import br.edu.ufop.web.ticket.sales.enums.EnumEventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventDTO {

    private UUID id;

    private EnumEventType type;

    private String description;

    private LocalDateTime date;

    private LocalDateTime startSales;

    private LocalDateTime endSales;

    private float price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
