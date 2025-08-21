package br.edu.ufop.web.ticket.sales.service;

import br.edu.ufop.web.ticket.sales.converter.SaleConverter;
import br.edu.ufop.web.ticket.sales.domain.SaleDomain;
import br.edu.ufop.web.ticket.sales.dtos.CreateSaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.DeleteSaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.SaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.UpdateSaleDTO;
import br.edu.ufop.web.ticket.sales.dtos.externals.notifications.CreateNotificationDTO;
import br.edu.ufop.web.ticket.sales.dtos.externals.notifications.NotificationDTO;
import br.edu.ufop.web.ticket.sales.dtos.externals.users.UserDTO;
import br.edu.ufop.web.ticket.sales.enums.EnumSalesType;
import br.edu.ufop.web.ticket.sales.models.EventModel;
import br.edu.ufop.web.ticket.sales.models.SaleModel;
import br.edu.ufop.web.ticket.sales.repositories.IEventRepository;
import br.edu.ufop.web.ticket.sales.repositories.ISaleRepository;
import br.edu.ufop.web.ticket.sales.service.clients.NotificationServiceClient;
import br.edu.ufop.web.ticket.sales.service.clients.UserServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SaleService {

    private final ISaleRepository saleRepository;

    private final IEventRepository eventRepository;

    //private final IUserRepository userRepository;

    private final NotificationServiceClient notificationServiceClient;

    private final UserServiceClient userServiceClient;

   @Transactional(readOnly = true)
    public List<SaleDTO> getAllSales() {
        
        List<SaleModel> saleModels = saleRepository.findAllWithEvent(); 

        return saleModels.stream()
            .map(sale -> {
                UserDTO user = fetchUserDTO(sale.getUserId());
                return SaleConverter.toSaleDTO(sale, user);
            })
            .collect(Collectors.toList());
    }

    public SaleDTO getSaleById(String saleId) {
        
        UUID uuid = UUID.fromString(saleId);
        return saleRepository.findById(uuid).map(saleModel -> {
            UserDTO userDTO = fetchUserDTO(saleModel.getUserId());
            return SaleConverter.toSaleDTO(saleModel, userDTO);
        }).orElse(null);

       // Optional<SaleModel> optionalSaleModel = saleRepository.findById(uuid);
        //if (optionalSaleModel.isPresent()){
         //   SaleModel saleModel = optionalSaleModel.get();
          //  return SaleConverter.toSaleDTO(saleModel);
        //}

        //return null;
    }
    
    @Transactional
    public SaleDTO createSale(CreateSaleDTO createSaleDTO) {

        EventModel eventModel = eventRepository.findById(createSaleDTO.getEventId())
            .orElseThrow(() -> new RuntimeException("Event not found"));


        //UserModel userModel = userRepository.findById(createSaleDTO.getUserId())
        //    .orElseThrow(() -> new RuntimeException("User not found with ID: " + createSaleDTO.getUserId()));

        /*UserDTO userDTO = fetchUserDTO(createSaleDTO.getUserId());
        if (userDTO == null){
            throw new RuntimeException("User not found with ID: " + createSaleDTO.getUserId());
        }*/

        UserDTO userDTO;
            try {
                userDTO = userServiceClient.findUserById(createSaleDTO.getUserId());
            } catch (Exception e) {
                throw new RuntimeException("User not found with ID: " + createSaleDTO.getUserId(), e);
            }

            if (userDTO == null) {
                throw new RuntimeException("User not found with ID: " + createSaleDTO.getUserId());
            }

        SaleDomain saleDomain = SaleConverter.toSaleDomain(createSaleDTO);

        //saleDomain.setEvent(eventModel);
        saleDomain.setSaleDate(LocalDateTime.now());
        saleDomain.setSaleStatus(
                createSaleDTO.getSaleStatus() != null
                        ? EnumSalesType.fromCode(createSaleDTO.getSaleStatus())
                        : EnumSalesType.EM_ABERTO
        );

        SaleModel saleModel = SaleConverter.toSaleModel(saleDomain, eventModel);
        saleModel = saleRepository.save(saleModel);
        sendNotification(saleModel);
        
        
        return SaleConverter.toSaleDTO(saleModel, userDTO);
    }

    @Transactional
    public SaleDTO updateSale(UpdateSaleDTO updateSaleDTO) {

       /*Optional<SaleModel> optionalSaleModel = saleRepository.findById(updateSaleDTO.getId());

        if (!optionalSaleModel.isPresent()) {
            return null;
        }

        SaleModel saleModel = optionalSaleModel.get();

        saleModel.setSaleStatus(updateSaleDTO.getSaleStatus());

        return SaleConverter.toSaleDTO(saleRepository.save(saleModel, userDTO));*/

        return saleRepository.findById(updateSaleDTO.getId())
            .map(saleModel -> {
                saleModel.setSaleStatus(EnumSalesType.fromCode(updateSaleDTO.getSaleStatus()));
                    SaleModel updatedSaleModel = saleRepository.save(saleModel);
                    UserDTO userDTO = fetchUserDTO(updatedSaleModel.getUserId());
                    return SaleConverter.toSaleDTO(updatedSaleModel, userDTO);
                })
                .orElseThrow(() -> new RuntimeException("Sale not found with ID: " + updateSaleDTO.getId()));
    }

    public void deleteSale(DeleteSaleDTO deleteSaleDTO) {
        
        Optional<SaleModel> optionalSaleModel = saleRepository.findById(deleteSaleDTO.id());
        
        if (optionalSaleModel.isEmpty()) {
            throw new RuntimeException("Sale not found");
        }   

        saleRepository.delete(optionalSaleModel.get());

    }

    private UserDTO fetchUserDTO(UUID userId) {
        if (userId == null) return null;
        try {
            // Assumindo que o seu UserServiceClient tem um método "findUserById"
            return userServiceClient.findUserById(userId);
        } catch (Exception e) {
            System.err.println("Falha ao buscar utilizador com ID: " + userId + ". Erro: " + e.getMessage());
            return null;
        }
    }

    private void sendNotification(SaleModel saleModel) {

        CreateNotificationDTO createNotificationDTO = new CreateNotificationDTO( saleModel.getUserId(),
            "SALES",
            "MESSAGE",
            "Ticket sales",
            "Event " + saleModel.getEvent().getDescription()
        );

        NotificationDTO notificationDTO = notificationServiceClient.create(createNotificationDTO);

        System.out.println(notificationDTO);

    }

}

