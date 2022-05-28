package com.weedro.whereismytime.service;

import com.weedro.whereismytime.domain.dto.WastedTimeDto;
import com.weedro.whereismytime.domain.dto.WastedTimePostDto;
import com.weedro.whereismytime.domain.dto.WastedTimeSummaryDto;
import com.weedro.whereismytime.domain.entity.CrackerPupper;
import com.weedro.whereismytime.mapper.WastedTimeMapper;
import com.weedro.whereismytime.repository.WastedTimeRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record WastedTimeService(WastedTimeRepository wastedTimeRepository,
                                WastedTimeMapper wastedTimeMapper) {

    public Long saveTimeTrackUpdate(String authorization,
        List<WastedTimePostDto> wastedTimePostDtos) {
        String userToken = resolveToken(authorization);

        List<CrackerPupper> crackerPuppers = wastedTimeMapper.wastedTimePostDtoToWastedTimes(
            userToken,
            wastedTimePostDtos);

        return (long) wastedTimeRepository.saveAll(crackerPuppers).size();
    }

    public List<WastedTimeSummaryDto> findUserWastedTime(String authorization) {
        String userToken = resolveToken(authorization);
        List<CrackerPupper> crackerPuppers = wastedTimeRepository.findAllByUserToken(userToken);
        List<WastedTimeDto> wastedTimeDtos = wastedTimeMapper.wastedTimeToWastedTimeDtos(
            crackerPuppers);

        Map<String, Long> wastedTimeByWindow = wastedTimeDtos.stream().collect(
            Collectors.groupingBy(WastedTimeDto::processName,
                Collectors.summingLong(WastedTimeDto::wastedTime)));

        return wastedTimeByWindow.entrySet().stream().filter(entry -> entry.getValue() > 0)
            .map(entry -> new WastedTimeSummaryDto(entry.getKey(), entry.getValue()))
            .sorted(Comparator.comparing(WastedTimeSummaryDto::wastedTime)).toList();
    }

    private String resolveToken(String authorization) {
        return authorization.replace("Bearer ", "");
    }

}
