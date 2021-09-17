package com.weedro.whereismytime.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("wasted_time")
public record WastedTime(@Id long id, String userToken, String windowName, long wastedTime) {

}
