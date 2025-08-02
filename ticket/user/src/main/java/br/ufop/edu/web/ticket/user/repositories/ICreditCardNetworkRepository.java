package br.ufop.edu.web.ticket.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufop.edu.web.ticket.user.models.CreditCardNetworkModel;
import java.util.UUID;

public interface ICreditCardNetworkRepository extends JpaRepository<CreditCardNetworkModel, UUID> {
    
    
}