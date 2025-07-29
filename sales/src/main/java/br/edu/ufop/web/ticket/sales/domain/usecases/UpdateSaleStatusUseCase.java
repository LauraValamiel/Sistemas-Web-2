package br.edu.ufop.web.ticket.sales.domain.usecases;

 
import br.edu.ufop.web.ticket.sales.domain.SaleDomain;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateSaleStatusUseCase {

    private SaleDomain saleDomain;

    public void validate() {

        validateSaleStatus();

    }

    private void validateSaleStatus() {
        throw new UnsupportedOperationException("Status não encontrado.");
    }

   

}
