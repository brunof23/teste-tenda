package com.onebrain.tenda.application.usecases;

import com.onebrain.tenda.domain.repository.CouponRepository;
import com.onebrain.tenda.interfaces.controller.dto.CouponResponse;
import com.onebrain.tenda.interfaces.controller.mapper.CouponResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCouponsUseCase {

    private final CouponRepository repository;

    public List<CouponResponse> execute() {
        return repository.findAllActive()
                .stream()
                .map(CouponResponseMapper::toResponse)
                .toList();
    }
}

