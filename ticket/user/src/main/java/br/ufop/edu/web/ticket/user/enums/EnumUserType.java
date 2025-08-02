package br.ufop.edu.web.ticket.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum EnumUserType {

    //Cliente - comprar ticket
    //Organizadora - evento
    //Administrador
    CUSTOMER(1, "Customer"),
    ENTERPRISE(2, "Enterprise"),
    ADMIN(3, "Admin");

    //id - descrição
    private final Integer id;
    private final String description;

    EnumUserType(Integer id, String description) {
        this.id = id;
        this.description = description;
    }
}

/* 
@Getter
@AllArgsConstructor
public enum Level {

    LOW(id: L, name:"Nível Baixo"),
    MEDIUM(id: M, name:"Nível Médio"),
    HIGH(id: H, name:"Nível Alto");

    private Integer id;
    private String name;
}

public enum User {

    Level level;

    public String getLevel(){

        level.class.getEnumConstants()

        for (var l : level.values()) {
            l.
        }

        return level.getName();
    }


}**/

