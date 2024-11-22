package com.ecolab.ecolab.response;

import com.ecolab.ecolab.entity.DeviceEntity;

public record DeviceResponse(
        Long id,
        String brand,
        String model,
        String potency,
        String type
) {
    public DeviceResponse(DeviceEntity deviceEntity) {
        this(deviceEntity.getId(), deviceEntity.getBrand(), deviceEntity.getModel(),
                deviceEntity.getPotency(), deviceEntity.getType());
    }
}
