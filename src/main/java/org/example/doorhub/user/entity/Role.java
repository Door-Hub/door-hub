package org.example.doorhub.user.entity;

import lombok.Getter;

import java.util.Set;

@Getter
public enum Role {

    USER(Set.of(Permission.GET_ALL, Permission.CREATE, Permission.DELETE, Permission.UPDATE)),
    WORKER(Set.of(Permission.GET_ALL, Permission.CREATE, Permission.DELETE, Permission.UPDATE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
