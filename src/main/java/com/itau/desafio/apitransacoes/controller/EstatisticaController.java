package com.itau.desafio.apitransacoes.controller;

import com.itau.desafio.apitransacoes.model.Estatistica;
import com.itau.desafio.apitransacoes.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final TransacaoService transacaoService;

    public EstatisticaController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ResponseEntity<Estatistica> buscarEstatisticas() {
        Estatistica estatistica = transacaoService.getEstatisticas();
        return ResponseEntity.ok(estatistica);
    }
}