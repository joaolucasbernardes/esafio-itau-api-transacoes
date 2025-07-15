package com.itau.desafio.apitransacoes.controller;

import com.itau.desafio.apitransacoes.model.Transacao;
import com.itau.desafio.apitransacoes.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> registrarTransacao(@RequestBody @Valid Transacao transacao) {
        transacaoService.salvar(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTransacoes() {
        transacaoService.deletarTodas();
        return ResponseEntity.ok().build();
    }
}
