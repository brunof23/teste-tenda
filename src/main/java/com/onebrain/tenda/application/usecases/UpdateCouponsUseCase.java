package com.onebrain.tenda.application.usecases;

import com.onebrain.tenda.application.command.UpdateCouponCommand;
import com.onebrain.tenda.domain.exception.DomainException;
import com.onebrain.tenda.domain.model.Coupon;
import com.onebrain.tenda.domain.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCouponsUseCase {
    private final CouponRepository repository;

    public Coupon execute(UpdateCouponCommand cmd) {

        Coupon coupon = repository.findById(cmd.id())
                .orElseThrow(() -> new DomainException("Cupom não encontrado"));

        coupon.update(
                cmd.code(),
                cmd.description(),
                cmd.discountValue(),
                cmd.expirationDate(),
                cmd.published()
        );

        return repository.save(coupon);
    }
}
