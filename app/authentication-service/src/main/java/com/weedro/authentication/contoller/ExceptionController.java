package com.weedro.authentication.contoller;

import com.weedro.authentication.service.exception.WrongUserCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler(WrongUserCredentialsException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Mono<String> serverExceptionHandler(Exception ex) {
    return Mono.just(ex.getMessage());
  }
}
