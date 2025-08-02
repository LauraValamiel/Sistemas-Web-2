package br.edu.ufop.web.ticket.sales.dtos;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventDTO {

    private UUID id;
    private String description;
    private LocalDateTime date;
    private LocalDateTime startSales;
    private LocalDateTime endSales;
    private float price;
    private LocalDateTime updatedAt;

}
