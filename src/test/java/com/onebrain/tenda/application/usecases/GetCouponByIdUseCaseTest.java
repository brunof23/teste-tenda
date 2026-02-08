package com.onebrain.tenda.application.usecases;

import com.onebrain.tenda.domain.exception.DomainException;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCouponByIdUseCaseTest {

    @Mock
    private CouponRepository repository;

    @InjectMocks
    private GetCouponByIdUseCase useCase;

    @Test
    void shouldReturnCoupon() {

        var coupon = validCoupon();

        when(repository.findById(any()))
                .thenReturn(Optional.of(coupon));

        var result = useCase.execute(UUID.randomUUID());

        assertNotNull(result);
    }

    @Test
    void shouldThrowWhenDeleted() {

        var coupon = validCoupon();
        coupon.restoreDeleted(true);

        when(repository.findById(any()))
                .thenReturn(Optional.of(coupon));

        assertThrows(DomainException.class,
                () -> useCase.execute(UUID.randomUUID()));
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
