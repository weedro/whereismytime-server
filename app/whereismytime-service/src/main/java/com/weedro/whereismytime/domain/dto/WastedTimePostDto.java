package com.weedro.whereismytime.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public record WastedTimePostDto(@NotBlank String windowName, @NotBlank String processName,
                                @Positive Long wastedTime) {

}
