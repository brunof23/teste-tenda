package com.onebrain.tenda.interfaces.controller.error;

import com.onebrain.tenda.domain.exception.CouponAlreadyDeletedException;
import com.onebrain.tenda.domain.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiError> handleDomain(DomainException ex) {
        ex.printStackTrace();

        return build(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CouponAlreadyDeletedException.class)
    public ResponseEntity<ApiError> handleDeleted(CouponAlreadyDeletedException ex) {
        ex.printStackTrace();

        return build(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        ex.printStackTrace();

        return build(HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected error");
    }

    private ResponseEntity<ApiError> build(HttpStatus status, String msg) {

        ApiError error = new ApiError(
                msg,
                status.value(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(status).body(error);
    }
}
