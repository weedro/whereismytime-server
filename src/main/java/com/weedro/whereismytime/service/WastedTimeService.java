package com.weedro.whereismytime.service;

import com.weedro.whereismytime.domain.dto.WastedTimeDto;
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

    public Flux<WastedTimeDto> findUserWastedTime(String userId) {
        Flux<WastedTime> wastedTimeFlux =
            wastedTimeRepository.findAllByUserId(userId);

        return wastedTimeFlux.map(wastedTimeMapper::wastedTimeToWastedTimeDto);
    }

    public Mono<Long> saveTimeTrackUpdate(Flux<WastedTimeDto> wastedTimeDtoFlux) {
        Flux<WastedTime> wastedTimeFlux =
            wastedTimeDtoFlux.map(wastedTimeMapper::wastedTimeDtoToWastedTime);

        return wastedTimeRepository.saveAll(wastedTimeFlux).count();
    }
}
