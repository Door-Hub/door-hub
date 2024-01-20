package org.example.doorhub.notification;


import lombok.RequiredArgsConstructor;
import org.example.doorhub.notification.dto.NotificationRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequestDto requestDto){
        AbstractNotificationService service = notificationService.getService(requestDto.getNotificationType());
        boolean b = service.sendNotification(requestDto);
        System.out.println(b);
    }
}
