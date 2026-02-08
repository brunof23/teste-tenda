package com.onebrain.tenda.domain.repository;

import com.onebrain.tenda.domain.model.Coupon;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CouponRepository {
    Coupon save(Coupon coupon);
    Optional<Coupon> findById(UUID id);
    List<Coupon> findAllActive();
    List<Coupon> findAllDeleted();

}
