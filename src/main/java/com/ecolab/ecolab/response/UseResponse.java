package com.ecolab.ecolab.response;

import com.ecolab.ecolab.entity.UseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UseResponse(
    Long id,
    LocalDateTime on,
    LocalDateTime off,
    Long userDeviceId,
    BigDecimal valueSpent
) {
    public UseResponse(UseEntity useEntity) {
        this(useEntity.getId(), useEntity.getOn(),useEntity.getOff(),useEntity.getUserDevice().getId(),useEntity.getValueSpent());
    }
}
