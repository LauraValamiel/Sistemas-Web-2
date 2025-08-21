package br.edu.ufop.web.ticket.sales.service.clients;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

import br.edu.ufop.web.ticket.sales.dtos.externals.users.UserDTO;

@FeignClient(name = "users-service", path = "/users")
public interface UserServiceClient {

    @GetMapping
    List<UserDTO> getAllUsers();

    @GetMapping("/{id}") 
    UserDTO findUserById(@PathVariable("id") UUID id);

    

}
