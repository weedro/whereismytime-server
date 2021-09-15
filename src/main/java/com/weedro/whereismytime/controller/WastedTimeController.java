package com.weedro.whereismytime.controller;

import com.weedro.whereismytime.config.Api;
import com.weedro.whereismytime.domain.dto.WastedTimeDto;
import com.weedro.whereismytime.service.WastedTimeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Api.BASE_URL + "/track")
public record WastedTimeController(WastedTimeService wastedTimeService) {

    @GetMapping(
        value = "/{userId}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<WastedTimeDto> userWastedTime(@PathVariable String userId) {
        return wastedTimeService.findUserWastedTime(userId);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Long> trackTime(@RequestBody Flux<WastedTimeDto> wastedTimeDtoList) {
        return wastedTimeService.saveTimeTrackUpdate(wastedTimeDtoList);
    }
}
