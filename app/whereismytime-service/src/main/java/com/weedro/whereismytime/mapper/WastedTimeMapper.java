package com.weedro.whereismytime.mapper;

import com.weedro.whereismytime.domain.dto.WastedTimeDto;
import com.weedro.whereismytime.domain.dto.WastedTimePostDto;
import com.weedro.whereismytime.domain.entity.WastedTime;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WastedTimeMapper {

    WastedTime wastedTimePostDtoToWastedTime(String userToken, WastedTimePostDto wastedTimePostDto);

    WastedTimeDto wastedTimeToWastedTimeDto(WastedTime wastedTime);
}
