package org.example.doorhub.notification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationMessagingService notificationMessagingService;

    @PostMapping
    public String sendNotificationByToken(@RequestBody NotificationMessage notificationMessage){
        return notificationMessagingService.sendNotificationByToken(notificationMessage);
    }
}
