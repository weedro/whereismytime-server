package com.weedro.authentication.mapper;

import com.weedro.authentication.domain.dto.AuthResponseDto;
import com.weedro.authentication.domain.entity.AuthenticatedUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthenticatedUserMapper {

    AuthenticatedUser userCredentialsToAuthenticatedUser(String token, String username,
        String password);

    AuthResponseDto authenticatedUserToAuthResponseDto(AuthenticatedUser authenticatedUser);
}
