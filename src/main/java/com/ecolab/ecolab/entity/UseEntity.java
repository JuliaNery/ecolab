package com.ecolab.ecolab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_use")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime on;
    private LocalDateTime off;
    @ManyToOne
    private UserDeviceEntity userDevice;
    private BigDecimal valueSpent;
}
