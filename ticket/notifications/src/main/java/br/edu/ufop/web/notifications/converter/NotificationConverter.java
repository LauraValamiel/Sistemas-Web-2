package br.edu.ufop.web.notifications.converter;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import br.edu.ufop.web.notifications.model.NotificationModel;
import br.edu.ufop.web.notifications.dtos.CreateNotificationDTO;
import br.edu.ufop.web.notifications.domain.NotificationDomain;
import br.edu.ufop.web.notifications.dtos.NotificationDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationConverter {

    public static NotificationDomain toNotificationDomain(CreateNotificationDTO createNotificationDTO) {

        return NotificationDomain.builder()
                .userId(createNotificationDTO.userId())
                .service(createNotificationDTO.service())
                .notificationType(createNotificationDTO.notificationType())
                .subject(createNotificationDTO.subject())
                .content(createNotificationDTO.content())
                .build();
    }

    public static NotificationModel toNotificationModel(NotificationDomain notifications){

        return NotificationModel.builder()
                .id(notifications.getId())
                .userId(notifications.getUserId())
                .service(notifications.getService())
                .notificationType(notifications.getNotificationType())
                .subject(notifications.getSubject())
                .content(notifications.getContent())
                .sentAt(notifications.getSentAt())
                .readAt(notifications.getReadAt())
                .build();

    }

    public static NotificationDTO tonotificationDTO(NotificationModel notificationModel){

        return new NotificationDTO(
                notificationModel.getId(),
                notificationModel.getUserId(),
                notificationModel.getService(),
                notificationModel.getNotificationType(),
                notificationModel.getSubject(),
                notificationModel.getContent(),
                notificationModel.getSentAt(),
                notificationModel.getReadAt()
        );



    }

    
    
}
