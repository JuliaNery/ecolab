package com.ecolab.ecolab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_user_device")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDeviceEntity {
    private Long id;
    private UserEntity user;
    private DeviceEntity device;
}
