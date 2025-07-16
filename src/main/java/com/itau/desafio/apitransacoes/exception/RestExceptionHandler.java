package com.itau.desafio.apitransacoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroDeValidacaoResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<CampoComErro> erros = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(error -> {
            CampoComErro erro = new CampoComErro(error.getField(), error.getDefaultMessage());
            erros.add(erro);
        });
        logger.warn("Requisição recebida com dados de validação inválidos: {}", erros);

        return new ErroDeValidacaoResponse(erros);
    }
}