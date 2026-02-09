package com.onebrain.tenda.domain.model;

import com.onebrain.tenda.domain.exception.CouponAlreadyDeletedException;
import com.onebrain.tenda.domain.exception.DomainException;
import lombok.Data;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class Coupon {

    private UUID id;
    private String code;
    private String description;
    private BigDecimal discountValue;
    private LocalDate expirationDate;

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
        String normalized = Normalizer.normalize(raw, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        String clean = normalized
                .replaceAll("[^a-zA-Z0-9]", "")
                .toUpperCase();

        if (clean.length() < 6)
            throw new DomainException("O código precisa ter 6 caracteres");

        return clean.substring(0, 6);
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

    public void update(
            String code,
            String description,
            BigDecimal discountValue,
            LocalDate expirationDate,
            boolean published
    ) {

        if (deleted)
            throw new DomainException("Cupon deletado não pode ser atualizado");

        this.code = normalize(code);

        if (discountValue.compareTo(BigDecimal.valueOf(0.5)) < 0)
            throw new DomainException("Desconto mínimo é de 0.5");

        if (expirationDate.isBefore(LocalDate.now()))
            throw new DomainException("Data de expiração não pode ser no passado");

        this.description = description;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
        this.published = published;
    }

}

