package com.itau.desafio.apitransacoes.exception;

import java.util.List;

public record ErroDeValidacaoResponse(List<CampoComErro> erros) {
}

record CampoComErro(String campo, String mensagem) {
}