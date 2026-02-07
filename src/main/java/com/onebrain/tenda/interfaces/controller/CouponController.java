package com.onebrain.tenda.interfaces.controller;

import com.onebrain.tenda.application.command.CreateCouponCommand;
import com.onebrain.tenda.application.usecases.*;
import com.onebrain.tenda.domain.model.Coupon;
import com.onebrain.tenda.interfaces.controller.dto.CouponResponse;
import com.onebrain.tenda.interfaces.controller.dto.CreateCouponRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CreateCouponUseCase createUseCase;
    private final DeleteCouponUseCase deleteUseCase;
    private final GetCouponByIdUseCase getByIdUseCase;
    private final GetAllCouponsUseCase getAllUseCase;

    @PostMapping
    public Coupon create(@RequestBody CreateCouponRequest req) {

        return createUseCase.execute(
                new CreateCouponCommand(
                        req.code(),
                        req.description(),
                        req.discountValue(),
                        req.expirationDate(),
                        req.published()
                )
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        deleteUseCase.execute(id);
    }

    @GetMapping("/{id}")
    public CouponResponse getById(@PathVariable UUID id) {
        return getByIdUseCase.execute(id);
    }

    @GetMapping
    public List<CouponResponse> getAll() {
        return getAllUseCase.execute();
    }
}
