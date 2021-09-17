package com.weedro.whereismytime.mapper;

import com.weedro.whereismytime.domain.dto.WastedTimeDto;
import com.weedro.whereismytime.domain.dto.WastedTimePostDto;
import com.weedro.whereismytime.domain.entity.WastedTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WastedTimeMapper {

  WastedTime wastedTimeDtoToWastedTime(WastedTimeDto wastedTimeDto);

  @Mapping(target = "userToken", source = "userToken")
  WastedTime wastedTimePostDtoToWastedTime(String userToken, WastedTimePostDto wastedTimePostDto);

  WastedTimeDto wastedTimeToWastedTimeDto(WastedTime wastedTime);
}
