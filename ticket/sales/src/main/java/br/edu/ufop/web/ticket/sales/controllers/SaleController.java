package br.edu.ufop.web.ticket.sales.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufop.web.ticket.sales.dtos.CreateSaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.DeleteSaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.SaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.UpdateSaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.externals.users.UserDTO;
import br.edu.ufop.web.ticket.sales.service.SaleService;
import br.edu.ufop.web.ticket.sales.service.clients.UserServiceClient;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;
    private final UserServiceClient userServiceClient;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Sales service is running");
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(
            userServiceClient.getAllUsers()
        );
    }

    @GetMapping
    public ResponseEntity<List<SaleDTO>> getAllSales() {

        List<SaleDTO> salesList = saleService.getAllSales();

        return ResponseEntity.ok(salesList);
    }

    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@RequestBody CreateSaleDTO createSaleDTO) {

        SaleDTO saleDTO = saleService.createSale(createSaleDTO);
        return ResponseEntity.ok(saleDTO);
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable(value = "saleId") String saleId) {

        SaleDTO saleDTO = saleService.getSaleById(saleId);

        if (saleDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(saleDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SaleDTO>> getSalesByUserId(@PathVariable(value = "userId") String userId) {

        List<SaleDTO> salesList = saleService.getAllSales()
            .stream()
            .filter(sale -> sale.getUserId().toString().equals(userId))
            .toList();

        return ResponseEntity.ok(salesList);
    }

    @PutMapping("/saleStatus")
    public ResponseEntity<SaleDTO> updateSaleStatus(@RequestBody UpdateSaleDTO updateSaleDTO) {

        SaleDTO saleDTO = saleService.updateSale(updateSaleDTO);

        if (saleDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(saleDTO);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Object> deleteSale(@RequestBody DeleteSaleDTO deleteSaleDTO) {

        saleService.deleteSale(deleteSaleDTO);

        return ResponseEntity.ok("Sale has been deleted.");
    }


}
