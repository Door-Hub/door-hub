package org.example.doorhub.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationMessage {
    private String recipientToken;
    private String title;
    private String body;
    private String image;
    private Map<String, String> data;
}
