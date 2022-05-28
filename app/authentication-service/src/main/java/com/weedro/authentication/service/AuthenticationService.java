package com.weedro.authentication.service;

import com.google.common.hash.Hashing;
import com.weedro.authentication.domain.dto.AuthRequestDto;
import com.weedro.authentication.domain.dto.AuthResponseDto;
import com.weedro.authentication.domain.entity.AuthenticatedUser;
import com.weedro.authentication.mapper.AuthenticatedUserMapper;
import com.weedro.authentication.repository.AuthenticatedUserRepository;
import com.weedro.authentication.service.exception.WrongUserCredentialsException;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public record AuthenticationService(AuthenticatedUserRepository authenticatedUserRepository,
                                    AuthenticatedUserMapper authenticatedUserMapper) {

    public Mono<AuthResponseDto> getToken(AuthRequestDto authRequestDto) {
        final String username = authRequestDto.username();
        final String password = authRequestDto.password();

        return authenticatedUserRepository.existsByUsername(username).flatMap(userPresent -> {
            String encryptedPassword = passwordEncryptor(password);

            if (Boolean.TRUE.equals(userPresent)) {
                return authenticatedUserRepository.findByUsernameAndPassword(username,
                        encryptedPassword)
                    .flatMap(user -> {
                        AuthResponseDto authResponse = authenticatedUserMapper.authenticatedUserToAuthResponseDto(
                            user);

                        return Mono.just(authResponse);
                    })
                    .switchIfEmpty(Mono.error(new WrongUserCredentialsException(
                        String.format("Wrong user credentials for username: [%s]", username))));
            }

            String token = generateToken(username);

            AuthenticatedUser authenticatedUser = authenticatedUserMapper.userCredentialsToAuthenticatedUser(
                token, username, encryptedPassword);

            return authenticatedUserRepository.save(authenticatedUser)
                .map(authenticatedUserMapper::authenticatedUserToAuthResponseDto);
        });
    }

    private String passwordEncryptor(String password) {
        return Hashing.murmur3_128()
            .hashString(password, StandardCharsets.UTF_8)
            .toString();
    }

    private String generateToken(String username) {
        return Hashing.murmur3_32()
            .hashString(username, StandardCharsets.UTF_8)
            .toString();
    }
}
