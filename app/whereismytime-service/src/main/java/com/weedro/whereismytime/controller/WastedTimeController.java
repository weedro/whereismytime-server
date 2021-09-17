package com.weedro.whereismytime.controller;

import com.weedro.whereismytime.config.Api;
import com.weedro.whereismytime.domain.dto.WastedTimeDto;
import com.weedro.whereismytime.domain.dto.WastedTimePostDto;
import com.weedro.whereismytime.domain.type.ResultType;
import com.weedro.whereismytime.service.WastedTimeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Api.BASE_URL + "/track")
public record WastedTimeController(WastedTimeService wastedTimeService) {

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<WastedTimeDto> userWastedTime(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
      @RequestParam("type") ResultType type) {

    return switch (type) {
      case full -> wastedTimeService.findUserWastedTime(authorization);
      case summary -> wastedTimeService.findUserWastedTimeSummary(authorization);
    };
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Long> trackTime(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
      @RequestBody Flux<WastedTimePostDto> wastedTimePostDtoFlux) {
    return wastedTimeService.saveTimeTrackUpdate(authorization, wastedTimePostDtoFlux);
  }
}
