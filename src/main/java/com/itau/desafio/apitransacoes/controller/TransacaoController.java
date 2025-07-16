package com.itau.desafio.apitransacoes.controller;

import com.itau.desafio.apitransacoes.model.Transacao;
import com.itau.desafio.apitransacoes.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Registra uma nova transação", description = "Recebe e armazena uma nova transação financeira.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação registrada com sucesso", content = @Content),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (JSON mal formatado)", content = @Content),
            @ApiResponse(responseCode = "422", description = "Falha de validação nos dados da transação", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> registrarTransacao(@RequestBody @Valid Transacao transacao) {
        transacaoService.salvar(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Deleta todas as transações", description = "Remove todos os registros de transações da memória.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas as transações foram deletadas", content = @Content)
    })
    @DeleteMapping
    public ResponseEntity<Void> deletarTransacoes() {
        transacaoService.deletarTodas();
        return ResponseEntity.ok().build();
    }
}
