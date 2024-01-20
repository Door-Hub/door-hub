package org.example.doorhub.notification;

import org.example.doorhub.notification.dto.NotificationRequestDto;

public abstract class AbstractNotificationService {

    public abstract boolean supports(NotificationType notificationType);

    public abstract boolean sendNotification(NotificationRequestDto notificationRequestDto);

}
