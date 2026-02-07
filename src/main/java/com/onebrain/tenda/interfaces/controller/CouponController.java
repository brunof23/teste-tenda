package com.onebrain.tenda.interfaces.controller;

import com.onebrain.tenda.application.command.CreateCouponCommand;
import com.onebrain.tenda.application.usecases.*;
import com.onebrain.tenda.domain.model.Coupon;
import com.onebrain.tenda.interfaces.controller.dto.CouponResponse;
import com.onebrain.tenda.interfaces.controller.dto.CreateCouponRequest;
import com.onebrain.tenda.interfaces.controller.mapper.CouponResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
@Tag(name = "Coupons", description = "API de Gerenciamento de Cupons da Tenda")
public class CouponController {

    private final CreateCouponUseCase createUseCase;
    private final DeleteCouponUseCase deleteUseCase;
    private final GetCouponByIdUseCase getByIdUseCase;
    private final GetAllCouponsUseCase getAllUseCase;

    @Operation(summary = "Create coupon")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CouponResponse create(@RequestBody CreateCouponRequest req) {

        var coupon = createUseCase.execute(
                new CreateCouponCommand(
                        req.code(),
                        req.description(),
                        req.discountValue(),
                        req.expirationDate(),
                        req.published()
                )
        );

        return CouponResponseMapper.toResponse(coupon);
    }


    @Operation(summary = "Soft delete coupon")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        deleteUseCase.execute(id);
    }

    @Operation(summary = "Get coupon by ID")
    @GetMapping("/{id}")
    public CouponResponse getById(@PathVariable UUID id) {
        return getByIdUseCase.execute(id);
    }

    @Operation(summary = "Get all coupons")
    @GetMapping
    public List<CouponResponse> getAll() {
        return getAllUseCase.execute();
    }
}
