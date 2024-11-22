package com.ecolab.ecolab.request;

public record UserRequest(
        String name,
        String email,
        String password,
        String cep
) {
}
