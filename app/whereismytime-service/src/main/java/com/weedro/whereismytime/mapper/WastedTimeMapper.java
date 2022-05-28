package com.weedro.whereismytime.mapper;

import com.weedro.whereismytime.domain.dto.WastedTimeDto;
import com.weedro.whereismytime.domain.dto.WastedTimePostDto;
import com.weedro.whereismytime.domain.entity.WastedTime;
import java.util.Collections;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WastedTimeMapper {

    WastedTime wastedTimePostDtoToWastedTime(String userToken, WastedTimePostDto wastedTimePostDto);

    default List<WastedTime> wastedTimePostDtoToWastedTimes(String userToken,
        List<WastedTimePostDto> wastedTimePostDtos) {
        if (wastedTimePostDtos == null) {
            return Collections.emptyList();
        }
        return wastedTimePostDtos.stream()
            .map(wastedTimePostDto -> wastedTimePostDtoToWastedTime(userToken, wastedTimePostDto))
            .toList();
    }

    List<WastedTimeDto> wastedTimeToWastedTimeDtos(List<WastedTime> wastedTime);
}
