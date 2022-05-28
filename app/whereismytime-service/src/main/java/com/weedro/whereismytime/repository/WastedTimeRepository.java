package com.weedro.whereismytime.repository;

import com.weedro.whereismytime.domain.entity.CrackerPupper;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WastedTimeRepository extends JpaRepository<CrackerPupper, Long> {

    List<CrackerPupper> findAllByUserToken(String userToken);

}
