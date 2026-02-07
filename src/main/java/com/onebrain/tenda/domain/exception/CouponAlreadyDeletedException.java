package com.onebrain.tenda.domain.exception;

public class CouponAlreadyDeletedException extends DomainException {
    public CouponAlreadyDeletedException() {
        super("Coupon already deleted");
    }
}
