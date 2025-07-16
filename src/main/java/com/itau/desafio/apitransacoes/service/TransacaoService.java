package com.itau.desafio.apitransacoes.service;

import com.itau.desafio.apitransacoes.model.Estatistica;
import com.itau.desafio.apitransacoes.model.Transacao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    private final List<Transacao> transacoes = new CopyOnWriteArrayList<>();

    public void salvar(Transacao transacao) {
        transacoes.add(transacao);
    }

    public void deletarTodas() {
        transacoes.clear();
    }

    public Estatistica getEstatisticas() {
        OffsetDateTime agora = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime limite = agora.minusSeconds(60);

        List<BigDecimal> valoresRecentes = transacoes.stream()
                .filter(t -> !t.dataHora().isBefore(limite) && t.dataHora().isBefore(agora))
                .map(Transacao::valor)
                .collect(Collectors.toList());

        if (valoresRecentes.isEmpty()) {
            return new Estatistica(0L, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        long count = valoresRecentes.size();
        BigDecimal sum = valoresRecentes.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal avg = sum.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP);
        BigDecimal min = valoresRecentes.stream().min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
        BigDecimal max = valoresRecentes.stream().max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);

        return new Estatistica(count, sum, avg, min, max);
    }
}
