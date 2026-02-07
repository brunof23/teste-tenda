package com.onebrain.tenda.domain.model;

import com.onebrain.tenda.domain.exception.CouponAlreadyDeletedException;
import com.onebrain.tenda.domain.exception.DomainException;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Coupon {

    private final UUID id;
    private final String code;
    private final String description;
    private final BigDecimal discountValue;
    private final LocalDate expirationDate;

    private boolean published;
    private boolean deleted;

    public Coupon(
            UUID id,
            String code,
            String description,
            BigDecimal discountValue,
            LocalDate expirationDate,
            boolean published
    ) {

        this.id = id;
        this.code = normalize(code);
        this.description = require(description);
        this.discountValue = validateDiscount(discountValue);
        this.expirationDate = validateExpiration(expirationDate);
        this.published = published;
        this.deleted = false;
    }

    private String normalize(String raw) {
        String clean = raw.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        if (clean.length() != 6) throw new DomainException("O código precisa ter 6 caracteres");
        return clean;
    }

    private String require(String v) {
        if (v == null || v.isBlank()) throw new DomainException("Campo obrigatório");
        return v;
    }

    private BigDecimal validateDiscount(BigDecimal v) {
        if (v == null || v.compareTo(BigDecimal.valueOf(0.5)) < 0)
            throw new DomainException("Desconto mínimo é de 0.5");
        return v;
    }

    private LocalDate validateExpiration(LocalDate d) {
        if (d == null || d.isBefore(LocalDate.now()))
            throw new DomainException("Data de expiração não pode ser no passado");
        return d;
    }

    public void softDelete() {
        if (deleted) throw new CouponAlreadyDeletedException();
        deleted = true;
    }

    public void restoreDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

