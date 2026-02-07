package com.onebrain.tenda.infrastructure.persistence.mapper;

import com.onebrain.tenda.domain.model.Coupon;
import com.onebrain.tenda.infrastructure.persistence.entity.CouponEntity;

public class CouponEntityMapper {

    public static Coupon toDomain(CouponEntity e) {

        Coupon c = new Coupon(
                e.getId(),
                e.getCode(),
                e.getDescription(),
                e.getDiscountValue(),
                e.getExpirationDate(),
                e.isPublished()
        );

        c.restoreDeleted(e.isDeleted());

        return c;
    }

    public static CouponEntity toEntity(Coupon c) {

        return new CouponEntity(
                c.getId(),
                c.getCode(),
                c.getDescription(),
                c.getDiscountValue(),
                c.getExpirationDate(),
                c.isPublished(),
                c.isDeleted()
        );
    }
}

