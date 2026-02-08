package com.onebrain.tenda.domain.model;

import com.onebrain.tenda.domain.exception.CouponAlreadyDeletedException;
import com.onebrain.tenda.domain.exception.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CouponTest {

    private Coupon coupon;

    @BeforeEach
    void setup() {
        coupon = new Coupon(
                UUID.randomUUID(),
                "abc123",
                "Cupom válido",
                BigDecimal.ONE,
                LocalDate.now().plusDays(5),
                true
        );
    }

    @Test
    void shouldCreateCouponSuccessfully() {
        assertEquals("ABC123", coupon.getCode());
        assertFalse(coupon.isDeleted());
        assertTrue(coupon.isPublished());
    }

    @Test
    void shouldFailWhenCodeInvalid() {
        assertThrows(DomainException.class, () ->
                new Coupon(
                        UUID.randomUUID(),
                        "abc",
                        "Teste",
                        BigDecimal.ONE,
                        LocalDate.now().plusDays(1),
                        false
                ));
    }

    @Test
    void shouldSoftDelete() {
        coupon.softDelete();
        assertTrue(coupon.isDeleted());
    }

    @Test
    void shouldThrowWhenDeletingTwice() {
        coupon.softDelete();
        assertThrows(CouponAlreadyDeletedException.class, coupon::softDelete);
    }

    @Test
    void shouldUpdateSuccessfully() {
        coupon.update(
                "xyz789",
                "Atualizado",
                BigDecimal.TEN,
                LocalDate.now().plusDays(10),
                false
        );

        assertEquals("XYZ789", coupon.getCode());
        assertEquals("Atualizado", coupon.getDescription());
    }

    @Test
    void shouldFailWhenDiscountInvalid() {
        assertThrows(DomainException.class, () ->
                new Coupon(
                        UUID.randomUUID(),
                        "ABC123",
                        "Teste",
                        BigDecimal.ZERO,
                        LocalDate.now().plusDays(1),
                        true
                ));
    }

    @Test
    void shouldFailWhenExpirationInvalid() {
        assertThrows(DomainException.class, () ->
                new Coupon(
                        UUID.randomUUID(),
                        "ABC123",
                        "Teste",
                        BigDecimal.ONE,
                        LocalDate.now().minusDays(1),
                        true
                ));
    }

    @Test
    void shouldFailUpdateWhenDeleted() {
        coupon.softDelete();

        assertThrows(DomainException.class, () ->
                coupon.update(
                        "XYZ789",
                        "Teste",
                        BigDecimal.ONE,
                        LocalDate.now().plusDays(5),
                        true
                ));
    }

    @Test
    void shouldFailUpdateWhenDiscountInvalid() {
        assertThrows(DomainException.class, () ->
                coupon.update(
                        "XYZ789",
                        "Teste",
                        BigDecimal.ZERO,
                        LocalDate.now().plusDays(5),
                        true
                ));
    }

    @Test
    void shouldFailUpdateWhenExpirationInvalid() {
        assertThrows(DomainException.class, () ->
                coupon.update(
                        "XYZ789",
                        "Teste",
                        BigDecimal.ONE,
                        LocalDate.now().minusDays(1),
                        true
                ));
    }

}
