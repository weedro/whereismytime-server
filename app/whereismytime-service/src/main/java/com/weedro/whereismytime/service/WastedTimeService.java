package com.weedro.whereismytime.service;

import com.weedro.whereismytime.domain.dto.WastedTimeDto;
import com.weedro.whereismytime.domain.dto.WastedTimePostDto;
import com.weedro.whereismytime.domain.dto.WastedTimeSummaryDto;
import com.weedro.whereismytime.domain.entity.WastedTime;
import com.weedro.whereismytime.mapper.WastedTimeMapper;
import com.weedro.whereismytime.repository.WastedTimeRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record WastedTimeService(WastedTimeRepository wastedTimeRepository,
                                WastedTimeMapper wastedTimeMapper) {

    public Long saveTimeTrackUpdate(String authorization,
        List<WastedTimePostDto> wastedTimePostDtos) {
        String userToken = resolveToken(authorization);

        List<WastedTime> wastedTimes = wastedTimeMapper.wastedTimePostDtoToWastedTimes(userToken,
            wastedTimePostDtos);

        return (long) wastedTimeRepository.saveAll(wastedTimes).size();
    }

    public List<WastedTimeSummaryDto> findUserWastedTime(String authorization) {
        String userToken = resolveToken(authorization);
        List<WastedTime> wastedTimes = wastedTimeRepository.findAllByUserToken(userToken);
        List<WastedTimeDto> wastedTimeDtos = wastedTimeMapper.wastedTimeToWastedTimeDtos(
            wastedTimes);

        Map<String, Long> wastedTimeByWindow = new HashMap<>();
        for (int i = 0; i < wastedTimeDtos.size(); i++) {
            WastedTimeDto currentLog = wastedTimeDtos.get(i);
            LocalDateTime nextLogTime = i + 1 == wastedTimeDtos.size() ? LocalDateTime.now()
                : wastedTimeDtos.get(i + 1).logTime();
            long minutesBetweenLogs = Duration.between(currentLog.logTime(), nextLogTime)
                .toMinutes();
            wastedTimeByWindow.merge(currentLog.windowName(), minutesBetweenLogs, Long::sum);
        }

        return wastedTimeByWindow.entrySet().stream().filter(entry -> entry.getValue() > 0)
            .map(entry -> new WastedTimeSummaryDto(entry.getKey(), entry.getValue()))
            .sorted(Comparator.comparing(WastedTimeSummaryDto::wastedTime)).toList();
    }

    private String resolveToken(String authorization) {
        return authorization.replace("Bearer ", "");
    }

}
