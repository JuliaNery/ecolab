package com.ecolab.ecolab.entity;

import jakarta.persistence.*;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private DeviceEntity device;
}
