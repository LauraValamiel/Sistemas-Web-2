package br.edu.ufop.web.ticket.sales.domain.usecases;

import java.time.LocalDateTime;
import java.util.UUID;


import br.edu.ufop.web.ticket.sales.domain.SaleDomain;
import br.edu.ufop.web.ticket.sales.models.EventModel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class CreateSaleUseCase {

    SaleDomain saleDomain;

    public void validate() {

        // Regras de negócio - conforme com o caso de uso
        validateUserId();
        validateSaleDate();

    }

    private void validateSaleDate() {

        EventModel event = saleDomain.getEventId();
        
        if (event == null) {
            throw new UnsupportedOperationException("O evento não está disponível para venda.");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startSales = event.getStartSales();
        LocalDateTime endSales = event.getEndSales();

        if (now.isBefore(startSales) || now.isAfter(endSales)) {
            throw new UnsupportedOperationException("Venda não disponível no momento.");
        }
    }

    private void validateUserId() {

        UUID userId = saleDomain.getUserId();

        if (userId == null || userId.toString().isEmpty()) {
            throw new UnsupportedOperationException("ID não válido.");
        }
    }

}
    

