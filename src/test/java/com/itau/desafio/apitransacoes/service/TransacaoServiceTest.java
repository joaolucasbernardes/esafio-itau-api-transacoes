package com.itau.desafio.apitransacoes.service;

import com.itau.desafio.apitransacoes.model.Estatistica;
import com.itau.desafio.apitransacoes.model.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

class TransacaoServiceTest {

    private TransacaoService transacaoService;
    private Clock clock;

    @BeforeEach
    void setUp() {
        // Cria um relógio fixo para ter controle sobre o tempo no teste
        Instant instanteFixo = Instant.parse("2025-07-16T12:00:00Z");
        clock = Clock.fixed(instanteFixo, ZoneOffset.UTC);
        transacaoService = new TransacaoService(clock);

        // Injeta o valor da janela de tempo manualmente para o teste
        ReflectionTestUtils.setField(transacaoService, "janelaEmSegundos", 60);
    }

    @Test
    void getEstatisticas_deveCalcularCorretamente_comMixDeTransacoes() {
        // Transações recentes e antigas misturadas
        OffsetDateTime agora = OffsetDateTime.now(clock);

        // Transações recentes (dentro da janela de 60s)
        transacaoService.salvar(new Transacao(new BigDecimal("100.50"), agora.minusSeconds(10)));
        transacaoService.salvar(new Transacao(new BigDecimal("200.00"), agora.minusSeconds(30)));

        // Transação antiga (fora da janela de 60s)
        transacaoService.salvar(new Transacao(new BigDecimal("50.00"), agora.minusSeconds(90)));

        // Ação
        Estatistica estatistica = transacaoService.getEstatisticas();

        // Verificação
        assertThat(estatistica.count()).isEqualTo(2);
        assertThat(estatistica.sum()).isEqualByComparingTo(new BigDecimal("300.50"));
        assertThat(estatistica.avg()).isEqualByComparingTo(new BigDecimal("150.25"));
        assertThat(estatistica.min()).isEqualByComparingTo(new BigDecimal("100.50"));
        assertThat(estatistica.max()).isEqualByComparingTo(new BigDecimal("200.00"));
    }

    @Test
    void getEstatisticas_deveRetornarZerado_quandoNaoHaTransacoesRecentes() {
        // Apenas transações antigas
        OffsetDateTime agora = OffsetDateTime.now(clock);
        transacaoService.salvar(new Transacao(new BigDecimal("99.99"), agora.minusSeconds(120)));

        // Ação
        Estatistica estatistica = transacaoService.getEstatisticas();

        // Verificação
        assertThat(estatistica.count()).isZero();
        assertThat(estatistica.sum()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(estatistica.avg()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(estatistica.min()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(estatistica.max()).isEqualByComparingTo(BigDecimal.ZERO);
    }
}