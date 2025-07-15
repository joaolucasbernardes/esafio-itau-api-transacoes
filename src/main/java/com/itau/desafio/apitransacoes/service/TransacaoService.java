package com.itau.desafio.apitransacoes.service;

import com.itau.desafio.apitransacoes.model.Transacao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TransacaoService {

    private final List<Transacao> transacoes = new CopyOnWriteArrayList<>();

    public void salvar(Transacao transacao) {
        transacoes.add(transacao);
    }

    public void deletarTodas() {
        transacoes.clear();
    }
}
