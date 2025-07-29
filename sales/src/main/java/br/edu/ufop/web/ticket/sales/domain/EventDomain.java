package br.edu.ufop.web.ticket.sales.domain;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import br.edu.ufop.web.ticket.sales.enums.EnumEventType;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDomain {

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
