package com.onebrain.tenda.application.usecases;

import com.onebrain.tenda.application.command.CreateCouponCommand;
import com.onebrain.tenda.domain.model.Coupon;
import com.onebrain.tenda.domain.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateCouponUseCase {

    private final CouponRepository repository;

    public Coupon execute(CreateCouponCommand cmd) {

        Coupon coupon = new Coupon(
                UUID.randomUUID(),
                cmd.code(),
                cmd.description(),
                cmd.discountValue(),
                cmd.expirationDate(),
                cmd.published()
        );

        return repository.save(coupon);
    }
}