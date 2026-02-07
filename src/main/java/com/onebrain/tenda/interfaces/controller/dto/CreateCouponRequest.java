package com.onebrain.tenda.interfaces.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Create coupon payload")
public record CreateCouponRequest(

        @Schema(example = "ABC123")
        String code,

        @Schema(example = "Desconto carnaval")
        String description,

        @Schema(example = "10.5")
        BigDecimal discountValue,

        @Schema(example = "2026-02-28")
        LocalDate expirationDate,

        @Schema(example = "true")
        boolean published
) {}

