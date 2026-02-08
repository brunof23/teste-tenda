package com.onebrain.tenda.application.usecases;

import com.onebrain.tenda.domain.model.Coupon;
import com.onebrain.tenda.domain.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCouponUseCase {

    private final CouponRepository repository;

    public void execute(UUID id) {

        Coupon coupon = repository.findById(id)
                .orElseThrow();

        coupon.softDelete();

        coupon.setPublished(false);

        repository.save(coupon);
    }
}
