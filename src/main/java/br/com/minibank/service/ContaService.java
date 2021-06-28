package br.com.minibank.service;

import br.com.minibank.database.data.Conta;

import java.util.Optional;

public interface ContaService {

    Conta criar(Conta conta);

    Optional<Conta> buscarContaPorNumeroConta(String numeroConta);

    Optional<Conta> buscarContaPorId(Long idConta);
}
