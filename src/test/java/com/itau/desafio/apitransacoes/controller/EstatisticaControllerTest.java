package com.itau.desafio.apitransacoes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.desafio.apitransacoes.model.Transacao;
import com.itau.desafio.apitransacoes.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EstatisticaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        transacaoService.deletarTodas();
    }

    @Test
    void getEstatisticas_deveRetornarStatus200_eCalcularValores() throws Exception {
        // Situação de teste:
        Transacao transacaoValida = new Transacao(new BigDecimal("150.80"), OffsetDateTime.now());

        // Adiciona uma transação via POST para ter dados
        mockMvc.perform(post("/transacao")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transacaoValida)));

        // Ação e Verificação
        mockMvc.perform(get("/estatistica"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.sum").value(150.80))
                .andExpect(jsonPath("$.avg").value(150.80))
                .andExpect(jsonPath("$.min").value(150.80))
                .andExpect(jsonPath("$.max").value(150.80));
    }
}