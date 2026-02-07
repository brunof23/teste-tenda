package com.onebrain.tenda.interfaces.controller.mapper;

import com.onebrain.tenda.domain.model.Coupon;
import com.onebrain.tenda.interfaces.controller.dto.CouponResponse;

public class CouponResponseMapper {

    public static CouponResponse toResponse(Coupon coupon) {
        return new CouponResponse(
                coupon.getId(),
                coupon.getCode(),
                coupon.getDescription(),
                coupon.getDiscountValue(),
                coupon.getExpirationDate(),
                coupon.isPublished()
        );
    }
}

