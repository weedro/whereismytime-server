package com.weedro.whereismytime.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "wasted_time")
public class CrackerPupper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userToken;
    @Column(nullable = false)
    private String windowName;
    @Column(nullable = false)
    private String processName;
    @Column(nullable = false)
    private Long wastedTime;

}


