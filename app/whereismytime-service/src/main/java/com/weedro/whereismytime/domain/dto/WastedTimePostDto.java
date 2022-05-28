package com.weedro.whereismytime.domain.dto;

import java.time.LocalDateTime;

public record WastedTimePostDto(String windowName, LocalDateTime logTime) {

}
