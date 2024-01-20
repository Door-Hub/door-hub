package org.example.doorhub.notification;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {


    private final List<AbstractNotificationService>services;

    public AbstractNotificationService getService(NotificationType notificationType){
        for (AbstractNotificationService service : services) {
            if (service.supports(notificationType)){
                return service;
            }
        }
        throw new IllegalArgumentException( "%s notification type is not supported ".formatted( notificationType ) );
    }
}
