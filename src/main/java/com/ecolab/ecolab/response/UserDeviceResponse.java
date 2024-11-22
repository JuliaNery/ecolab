package com.ecolab.ecolab.response;

import com.ecolab.ecolab.entity.UserDeviceEntity;

public record UserDeviceResponse(
        Long id,
        String name,
        Long userId,
        Long deviceId
) {
    public UserDeviceResponse(UserDeviceEntity userDeviceEntity) {
        this(userDeviceEntity.getId(), userDeviceEntity.getName(), userDeviceEntity.getUser().getId(),
                userDeviceEntity.getDevice().getId());
    }


}
