package com.onebrain.tenda.infrastructure.persistence;

import com.onebrain.tenda.infrastructure.persistence.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringCouponJpaRepository
        extends JpaRepository<CouponEntity, UUID> {
    List<CouponEntity> findByDeletedFalse();

}

