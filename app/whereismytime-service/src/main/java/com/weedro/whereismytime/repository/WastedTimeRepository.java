package com.weedro.whereismytime.repository;

import com.weedro.whereismytime.domain.entity.WastedTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WastedTimeRepository extends JpaRepository<WastedTime, Long> {

    List<WastedTime> findAllByUserToken(String userToken);

}
