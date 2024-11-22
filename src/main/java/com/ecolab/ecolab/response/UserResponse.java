package com.ecolab.ecolab.response;

import com.ecolab.ecolab.entity.UserEntity;

public record UserResponse(
        Long id,
        String name,
        String email,
        String cep
) {
    public UserResponse(UserEntity userEntity) {
        this(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getCep());
    }
}
