package br.edu.ufop.web.ticket.sales.enums;

import lombok.Getter;

@Getter
public enum EnumSalesType {

    //Em aberto, pago, cancelado, estornado, etc.
    EM_ABERTO(1, "Em aberto"),
    PAGO(2, "Pago"),
    CANCELADO(3, "Cancelado"),
    ESTORNADO(3, "Estornado");

    //id - descrição
    private final Integer id;
    private final String description;

    EnumSalesType(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

}
