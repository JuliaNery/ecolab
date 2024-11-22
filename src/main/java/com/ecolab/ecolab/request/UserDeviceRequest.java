package com.ecolab.ecolab.request;

public record UserDeviceRequest(
        String name,
        Long userId,
        Long deviceId
) {
}
