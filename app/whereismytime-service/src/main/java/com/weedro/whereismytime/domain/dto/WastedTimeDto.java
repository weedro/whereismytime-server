package com.weedro.whereismytime.domain.dto;

import java.time.LocalDateTime;

public record WastedTimeDto(String windowName, String processName, LocalDateTime startTime,
                            Long wastedTime) {

}
