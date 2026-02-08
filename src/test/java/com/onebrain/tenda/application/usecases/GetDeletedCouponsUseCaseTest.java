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
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetDeletedCouponsUseCaseTest {

    @Mock
    private CouponRepository repository;

    @InjectMocks
    private GetDeletedCouponsUseCase useCase;

    @Test
    void shouldReturnDeletedCoupons() {

        var coupon = validCoupon();
        coupon.restoreDeleted(true);

        when(repository.findAllDeleted())
                .thenReturn(List.of(coupon));

        var result = useCase.execute();

        assertEquals(1, result.size());
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
