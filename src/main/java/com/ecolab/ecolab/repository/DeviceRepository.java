package com.ecolab.ecolab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecolab.ecolab.entity.DeviceEntity;


@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
}
