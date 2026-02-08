package com.onebrain.tenda.application.usecases;

import com.onebrain.tenda.application.command.UpdateCouponCommand;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCouponsUseCaseTest {

    @Mock
    private CouponRepository repository;

    @InjectMocks
    private UpdateCouponsUseCase useCase;

    @Test
    void shouldUpdateCoupon() {

        var coupon = validCoupon();

        when(repository.findById(any()))
                .thenReturn(Optional.of(coupon));

        when(repository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        var cmd = new UpdateCouponCommand(
                coupon.getId(),
                "XYZ789",
                "Atualizado",
                BigDecimal.valueOf(2),
                LocalDate.now().plusDays(10),
                false
        );

        var result = useCase.execute(cmd);

        assertEquals("XYZ789", result.getCode());
        verify(repository).save(coupon);
    }

    @Test
    void shouldThrowWhenNotFound() {

        when(repository.findById(any()))
                .thenReturn(Optional.empty());

        var cmd = new UpdateCouponCommand(
                UUID.randomUUID(),
                "XYZ789",
                "Teste",
                BigDecimal.ONE,
                LocalDate.now().plusDays(5),
                true
        );

        assertThrows(DomainException.class,
                () -> useCase.execute(cmd));
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
