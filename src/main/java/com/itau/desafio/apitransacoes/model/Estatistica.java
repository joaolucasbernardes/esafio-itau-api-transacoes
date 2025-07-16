package com.itau.desafio.apitransacoes.model;

import java.math.BigDecimal;

public record Estatistica(
        long count,
        BigDecimal sum,
        BigDecimal avg,
        BigDecimal min,
        BigDecimal max) {
}