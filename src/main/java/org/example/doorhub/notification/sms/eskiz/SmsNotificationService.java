package org.example.doorhub.notification.sms.eskiz;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.doorhub.notification.sms.eskiz.dto.EskizRefreshResponseDto;
import org.example.doorhub.notification.sms.eskiz.dto.EskizSmsSentRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SmsNotificationService  {

    private final NotificationFeign notificationFeign;

    @Value("${notification-service.eskiz.access-token}")
    private String token;

    public boolean sendSms(String phone, String message) {
        try {
            notificationFeign.send(new EskizSmsSentRequestDto(phone, message), token);
            return true;
        }catch (FeignException.Forbidden | FeignException.Unauthorized ex){
            try {
                EskizRefreshResponseDto refreshToken = notificationFeign.refresh(token);
                token=refreshToken.getData().get("token");
                notificationFeign.send(new EskizSmsSentRequestDto(phone, message),token);
                return true;
            }catch (Exception e){
                log.error("Exception happend while refreshing eskiz jwt token", e);
                return false;
            }
        }catch (Exception e){
            log.error("Unable to send sms to number .Exception happend", e);
            return false;
        }
    }
}
