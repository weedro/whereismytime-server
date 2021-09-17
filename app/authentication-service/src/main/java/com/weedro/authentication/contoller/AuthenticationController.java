package com.weedro.authentication.contoller;

import com.weedro.authentication.config.Api;
import com.weedro.authentication.domain.dto.AuthRequestDto;
import com.weedro.authentication.domain.dto.AuthResponseDto;
import com.weedro.authentication.service.AuthenticationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Api.BASE_URL + "/auth")
public record AuthenticationController(AuthenticationService authenticationService) {

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<AuthResponseDto> getToken(@RequestBody AuthRequestDto authRequestDto) {
    return authenticationService.getToken(authRequestDto);
  }
}
