package com.onebrain.tenda.application.usecases;

import com.onebrain.tenda.domain.model.Coupon;
import com.onebrain.tenda.domain.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteCouponUseCaseTest {

    @Mock
    private CouponRepository repository;

    @InjectMocks
    private DeleteCouponUseCase useCase;

    @Test
    void shouldDeleteCoupon() {

        var coupon = validCoupon();

        when(repository.findById(any())).thenReturn(Optional.of(coupon));

        useCase.execute(UUID.randomUUID());

        assertTrue(coupon.isDeleted());
        assertFalse(coupon.isPublished());

        verify(repository).save(coupon);
    }

    private Coupon validCoupon() {
        return new Coupon(
                UUID.randomUUID(),
                "ABC123",
                "Teste",
                BigDecimal.ONE,
                LocalDate.now().plusDays(5),
                true
        );
    }
}
