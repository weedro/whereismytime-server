package com.weedro.whereismytime.controller;

import com.weedro.whereismytime.config.Api;
import com.weedro.whereismytime.domain.dto.WastedTimePostDto;
import com.weedro.whereismytime.domain.dto.WastedTimeSummaryDto;
import com.weedro.whereismytime.service.WastedTimeService;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(Api.BASE_URL + "/track")
public record WastedTimeController(WastedTimeService wastedTimeService) {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WastedTimeSummaryDto> userWastedTime(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {

        return wastedTimeService.findUserWastedTime(authorization);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long trackTime(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @RequestBody List<WastedTimePostDto> wastedTimePostDtoFlux) {
        return wastedTimeService.saveTimeTrackUpdate(authorization, wastedTimePostDtoFlux);
    }
}
