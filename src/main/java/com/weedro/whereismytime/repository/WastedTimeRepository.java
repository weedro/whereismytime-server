package com.weedro.whereismytime.repository;

import com.weedro.whereismytime.domain.dto.WastedTimeDto;
import com.weedro.whereismytime.domain.entity.WastedTime;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface WastedTimeRepository extends R2dbcRepository<WastedTime, Long> {

  Flux<WastedTime> findAllByUserId(String userId);

  @Query(value = """ 
          select window_name, sum(wasted_time) as wasted_time
          from wasted_time
          where user_id = :userId
          group by window_name
      """)
  Flux<WastedTimeDto> findAllByUserIdSummary(String userId);
}
