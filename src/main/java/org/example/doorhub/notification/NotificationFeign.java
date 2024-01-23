package org.example.doorhub.notification;

import org.example.doorhub.notification.dto.EskizRefreshResponseDto;
import org.example.doorhub.notification.dto.EskizSmsSentRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "notification", url = "notify.eskiz.uz/api")
public interface NotificationFeign {

    @PatchMapping("/auth/refresh")
    EskizRefreshResponseDto refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);

    @PostMapping("/message/sms/send")
    EskizRefreshResponseDto send(@RequestBody EskizSmsSentRequestDto requestDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);


}
