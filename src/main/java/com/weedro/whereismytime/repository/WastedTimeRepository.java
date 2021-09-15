package com.weedro.whereismytime.repository;

import com.weedro.whereismytime.domain.entity.WastedTime;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface WastedTimeRepository extends R2dbcRepository<WastedTime, Long> {

  Flux<WastedTime> findAllByUserId(String userId);
}
