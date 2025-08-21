package br.edu.ufop.web.ticket.sales.enums;

import lombok.Getter;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;

@Getter
public enum EnumEventType {
    //Palestra, show, teatro, cinema, festa, festival, etc.

    PALESTRA(1, "Palestra"),
    SHOW(2, "Show"),
    TEATRO(3, "Teatro"),
    CINEMA(4, "Cinema"),
    FESTA(5, "Festa"),
    FESTIVAL(6, "Festival"),
    OUTROS(7, "Outros");

    //id - descrição
    private final Integer id;
    private final String description;

    EnumEventType(Integer id, String description) {
        this.id = id;
        this.description = description;
    }


    public static EnumEventType fromId(Integer id) {
        return Arrays.stream(values())
                .filter(e -> e.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid EnumEventType id: " + id));
    }

    @JsonValue
    public Integer getId() {
        return id;
    }


}
