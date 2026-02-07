package com.onebrain.tenda.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponEntity {

    @Id
    private UUID id;

    private String code;
    private String description;
    private BigDecimal discountValue;
    private LocalDate expirationDate;

    private boolean published;
    private boolean deleted;
}
