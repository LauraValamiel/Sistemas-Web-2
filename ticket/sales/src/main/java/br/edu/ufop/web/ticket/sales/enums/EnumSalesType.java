package br.edu.ufop.web.ticket.sales.enums;

import lombok.Getter;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;

@Getter
public enum EnumSalesType {

    //Em aberto, pago, cancelado, estornado, etc.
    EM_ABERTO(1, "Em aberto"),
    PAGO(2, "Pago"),
    CANCELADO(3, "Cancelado"),
    ESTORNADO(4, "Estornado");

    //id - descrição
    private final Integer id;
    private final String description;

    EnumSalesType(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static EnumSalesType fromId(Integer id) {
        return Arrays.stream(values())
                .filter(e -> e.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid EnumSalesType id: " + id));
    }

    public static EnumSalesType fromCode(int code) {
        for (EnumSalesType status : values()) {
            if (status.getId() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código inválido para EnumSaleStatus: " + code);
    }

    @JsonValue
    public Integer getId() {
        return id;
    }


}
