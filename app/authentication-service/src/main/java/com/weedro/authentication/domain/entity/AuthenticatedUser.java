package com.weedro.authentication.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("authenticated_user")
public record AuthenticatedUser(@Id Long id, String token, String username, String password) {

}
