package com.weedro.whereismytime.service;

import com.weedro.whereismytime.domain.dto.WastedTimeDto;
import com.weedro.whereismytime.domain.dto.WastedTimePostDto;
import com.weedro.whereismytime.domain.entity.WastedTime;
import com.weedro.whereismytime.mapper.WastedTimeMapper;
import com.weedro.whereismytime.repository.WastedTimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public record WastedTimeService(WastedTimeRepository wastedTimeRepository,
                                WastedTimeMapper wastedTimeMapper) {

  public Flux<WastedTimeDto> findUserWastedTime(String authorization) {
    String userToken = resolveToken(authorization);
    Flux<WastedTime> wastedTimeFlux = wastedTimeRepository.findAllByUserToken(userToken);

    return wastedTimeFlux.map(wastedTimeMapper::wastedTimeToWastedTimeDto);
  }

  public Flux<WastedTimeDto> findUserWastedTimeSummary(String authorization) {
    String userToken = resolveToken(authorization);
    return wastedTimeRepository.findAllByUserTokenSummary(userToken);
  }

  public Mono<Long> saveTimeTrackUpdate(String authorization,
      Flux<WastedTimePostDto> wastedTimePostDtoFlux) {
    String userToken = resolveToken(authorization);

    Flux<WastedTime> wastedTimeFlux =
        wastedTimePostDtoFlux.map(
            wastedTimePostDto -> wastedTimeMapper.wastedTimePostDtoToWastedTime(userToken,
                wastedTimePostDto));

    return wastedTimeRepository.saveAll(wastedTimeFlux).count();
  }

  private String resolveToken(String authorization) {
    return authorization.replace("Bearer ", "");
  }
}
