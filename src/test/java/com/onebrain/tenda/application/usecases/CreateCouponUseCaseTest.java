package com.onebrain.tenda.application.usecases;

import com.onebrain.tenda.application.command.CreateCouponCommand;
import com.onebrain.tenda.domain.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCouponUseCaseTest {

    @Mock
    private CouponRepository repository;

    @InjectMocks
    private CreateCouponUseCase useCase;

    @Test
    void shouldCreateCoupon() {

        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        var cmd = new CreateCouponCommand(
                "ABC123",
                "Teste",
                BigDecimal.ONE,
                LocalDate.now().plusDays(5),
                true
        );

        var result = useCase.execute(cmd);

        assertNotNull(result);
        verify(repository).save(any());
    }
}
