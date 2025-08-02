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

public class UpdateUserPasswordDTO {

    private UUID id;
    private String email;
    private String oldPassword;
    private String newPassword;

}