package br.ufop.edu.web.ticket.user.dtos;

import java.util.UUID;
import lombok.Getter;  
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateUserDTO {

    private UUID id;
    private String name;
    private String creditCardNumber;
    private String email;
    private String password;
    private String city;
    


}