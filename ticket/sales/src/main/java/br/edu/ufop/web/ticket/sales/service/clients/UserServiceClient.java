package br.edu.ufop.web.ticket.sales.service.clients;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.openfeign.FeignClient;

import br.edu.ufop.web.ticket.sales.dtos.UserDTO;

@FeignClient("user-service")
public interface UserServiceClient {

    @GetMapping("/users")
    List<UserDTO> getAllUsers();

}
