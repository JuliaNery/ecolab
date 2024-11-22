package com.ecolab.ecolab.repository;

import com.ecolab.ecolab.entity.UseEntity;
import com.ecolab.ecolab.entity.UserDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UseRepository extends JpaRepository<UseEntity, Long> {
    Optional<UseEntity> findByUserDeviceAndOffIsNull(UserDeviceEntity userDevice);


    @Query("SELECT SUM(u.valueSpent) FROM UseEntity u WHERE u.userDevice.id = :userDeviceId AND u.on >= :dataInicio AND u.off <= :dataFim")
    BigDecimal sumValueSpentByUserDeviceIdAndDateRange(@Param("userDeviceId") Long userDeviceId, @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}
