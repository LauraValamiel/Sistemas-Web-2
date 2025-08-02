package br.ufop.edu.web.ticket.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufop.edu.web.ticket.user.converter.CreditCardNetworkConverter;
import br.ufop.edu.web.ticket.user.domain.CreditCardNetworkDomain;
import br.ufop.edu.web.ticket.user.dtos.creditCardNetwork.CreateCreditCardNetworkDTO;
import br.ufop.edu.web.ticket.user.dtos.creditCardNetwork.SimpleCreditCardNetworkDTO;
import br.ufop.edu.web.ticket.user.models.CreditCardNetworkModel;
import br.ufop.edu.web.ticket.user.repositories.ICreditCardNetworkRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreditCardNetworkService {

    private final ICreditCardNetworkRepository creditCardNetworkRepository;

    //Get all
    public List<SimpleCreditCardNetworkDTO> getALL(){
        
        List<CreditCardNetworkModel> creditCardNetworkModelList = creditCardNetworkRepository.findAll();

        return creditCardNetworkModelList.stream().map(CreditCardNetworkConverter::toSimpleCreditCardNetworkDTO).toList();

    }

    //Create
    public SimpleCreditCardNetworkDTO create(CreateCreditCardNetworkDTO createCreditCardNetworkDTO){

        CreditCardNetworkDomain domain = CreditCardNetworkConverter.toCreditCardNetworkDomain(createCreditCardNetworkDTO);

        //usecases - validações conforme as regras de negocio
        //name não pode ser nulo, name nao pode repetir, ...
        CreditCardNetworkModel model = CreditCardNetworkConverter.toCreditCardNetworkModel(domain);

        return CreditCardNetworkConverter.toSimpleCreditCardNetworkDTO(creditCardNetworkRepository.save(model));
        
        
    }

    //Get by id

    //Update

    //Delete

}
