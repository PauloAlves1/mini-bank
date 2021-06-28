package br.com.minibank.boundary.conta;

import br.com.minibank.database.data.Conta;
import br.com.minibank.database.repository.ContaRepository;
import br.com.minibank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository repository;

    @Override
    public Conta criar(Conta conta) {
        return repository.save(conta);
    }

    @Override
    public Optional<Conta> buscarContaPorNumeroConta(String numeroConta) {
        return repository.findByNumeroConta(numeroConta);
    }

    @Override
    public Optional<Conta> buscarContaPorId(Long idConta) {
        return repository.findById(idConta);
    }
}
