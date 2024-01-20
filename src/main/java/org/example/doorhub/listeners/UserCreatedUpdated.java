package org.example.doorhub.listeners;


import jakarta.persistence.PrePersist;
import org.example.doorhub.user.entity.User;

import java.time.LocalDateTime;

public class UserCreatedUpdated  {

    @PrePersist
    public void createdUpdated(User user){
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
    }


}
