package com.onebrain.tenda.infrastructure.persistence;

import com.onebrain.tenda.domain.model.Coupon;
import com.onebrain.tenda.domain.repository.CouponRepository;
import com.onebrain.tenda.infrastructure.persistence.mapper.CouponEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryJpa implements CouponRepository {

    private final SpringCouponJpaRepository jpa;

    @Override
    public Coupon save(Coupon coupon) {
        return CouponEntityMapper.toDomain(
                jpa.save(CouponEntityMapper.toEntity(coupon))
        );
    }

    @Override
    public Optional<Coupon> findById(UUID id) {
        return jpa.findById(id)
                .map(CouponEntityMapper::toDomain);
    }

    @Override
    public List<Coupon> findAllActive() {
        return jpa.findByDeletedFalse()
                .stream()
                .map(CouponEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Coupon> findAllDeleted() {
        return jpa.findByDeletedTrue()
                .stream()
                .map(CouponEntityMapper::toDomain)
                .toList();
    }
}
