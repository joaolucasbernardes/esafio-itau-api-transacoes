package com.itau.desafio.apitransacoes.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record Transacao(
        @NotNull
        @PositiveOrZero
        BigDecimal valor,

        @NotNull
        @PastOrPresent
        OffsetDateTime dataHora) {
}



