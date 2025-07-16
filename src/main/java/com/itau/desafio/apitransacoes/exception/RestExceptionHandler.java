package com.itau.desafio.apitransacoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroDeValidacaoResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<CampoComErro> erros = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(error -> {
            CampoComErro erro = new CampoComErro(error.getField(), error.getDefaultMessage());
            erros.add(erro);
        });

        return new ErroDeValidacaoResponse(erros);
    }
}