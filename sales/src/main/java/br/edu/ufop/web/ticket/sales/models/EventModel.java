package br.edu.ufop.web.ticket.sales.models;

import java.time.LocalDateTime;
import java.util.UUID;


import br.edu.ufop.web.ticket.sales.enums.EnumEventType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_events")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = true)
    private UUID id;

    @Column(nullable = true)
    private EnumEventType type;

    @Column(nullable = true)
    private String description;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime startSales;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime endSales;

    @Column(nullable = true)
    private float price;

    @Column(nullable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @PrePersist
    public void antesGravar() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void antesAtualizar(){
        this.updatedAt = LocalDateTime.now();
    }

    

}

