package com.onebrain.tenda.application.usecases;

import com.onebrain.tenda.domain.exception.DomainException;
import com.onebrain.tenda.domain.model.Coupon;
import com.onebrain.tenda.domain.repository.CouponRepository;
import com.onebrain.tenda.interfaces.controller.dto.CouponResponse;
import com.onebrain.tenda.interfaces.controller.mapper.CouponResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCouponByIdUseCase {

    private final CouponRepository repository;

    public CouponResponse execute(UUID id) {

        Coupon coupon = repository.findById(id)
                .filter(c -> !c.isDeleted())
                .orElseThrow(() -> new DomainException("Coupon not found"));

        return CouponResponseMapper.toResponse(coupon);
    }
}

