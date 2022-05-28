package com.weedro.whereismytime.domain.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("wasted_time")
public record WastedTime(@Id long id, String userToken, String windowName, LocalDateTime logTime) {

}
