package com.weedro.whereismytime.domain.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record WastedTimePostDto(@NotBlank String windowName, @NotBlank String processName,
                                @NotNull LocalDateTime startTime, @Positive Long wastedTime) {

}
