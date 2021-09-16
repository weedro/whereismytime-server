package com.weedro.whereismytime.mapper;

import com.weedro.whereismytime.domain.dto.WastedTimeDto;
import com.weedro.whereismytime.domain.entity.WastedTime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WastedTimeMapper {

  WastedTime wastedTimeDtoToWastedTime(WastedTimeDto wastedTimeDto);

  WastedTimeDto wastedTimeToWastedTimeDto(WastedTime wastedTime);
}
