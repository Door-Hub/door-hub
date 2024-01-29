package org.example.doorhub.notification;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationMessagingService notificationMessagingService;

    @PostMapping
    public String sendNotificationByToken(@RequestBody NotificationMessage notificationMessage) {
        return notificationMessagingService.sendNotificationByToken(notificationMessage);
    }
}
