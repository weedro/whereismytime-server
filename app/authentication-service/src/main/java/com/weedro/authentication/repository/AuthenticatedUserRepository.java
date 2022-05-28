package com.weedro.authentication.repository;

import com.weedro.authentication.domain.entity.AuthenticatedUser;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface AuthenticatedUserRepository extends R2dbcRepository<AuthenticatedUser, Long> {

    Mono<Boolean> existsByUsername(String username);

    Mono<AuthenticatedUser> findByUsernameAndPassword(String username, String password);
}
