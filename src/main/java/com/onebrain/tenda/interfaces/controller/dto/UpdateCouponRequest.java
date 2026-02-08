package com.onebrain.tenda.interfaces.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateCouponRequest(
        String code,
        String description,
        BigDecimal discountValue,
        LocalDate expirationDate,
        boolean published
) {}