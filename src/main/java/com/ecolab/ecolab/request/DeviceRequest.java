package com.ecolab.ecolab.request;

public record DeviceRequest(
        String brand,
        String model,
        String potency,
        String type

) {
}
