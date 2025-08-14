package br.edu.ufop.web.notifications.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufop.web.notifications.dtos.CreateNotificationDTO;
import br.edu.ufop.web.notifications.dtos.NotificationDTO;
import br.edu.ufop.web.notifications.service.NotificationService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Notification service is running");
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody CreateNotificationDTO createNotificationDTO) {

        NotificationDTO notificationDTO = notificationService.create(createNotificationDTO);
        return ResponseEntity.ok(notificationDTO);
        
    }
    
    
    
    
}
