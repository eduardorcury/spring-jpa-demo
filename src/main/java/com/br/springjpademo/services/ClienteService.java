package com.br.springjpademo.services;

import com.br.springjpademo.domain.Cliente;
import com.br.springjpademo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // OPERAÇÕES CRUD BÁSICAS

    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new RuntimeException("Objeto não encontrado"));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente insert(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> insertAll(List<Cliente> clientes) {
        return clienteRepository.saveAll(clientes);
    }

    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    // MÉTODOS COM PALAVRAS-CHAVE

    public List<Cliente> findByNome(String start) {
        return clienteRepository.findByNomeStartingWith(start);
    }

    public List<Cliente> findByIdade(Integer idade) {
        return clienteRepository.findByIdadeOrderByNomeAsc(idade);
    }

    public void deleteByNome(String nome, Integer idade) {
        clienteRepository.deleteByNomeIgnoreCaseOrIdade(nome, idade);
    }

    // MÉTODOS COM @QUERY
    public List<Cliente> nomeComecandoCom(String start) {
        return clienteRepository.nomeComecandoCom(start);
    }

    public List<Cliente> idadeIgualOrdemNome(Integer idade) {
        return clienteRepository.idadeIgualOrdemNome(idade);
    }

    public void deletarPorNomeOuIdade(String nome, Integer idade) {
        clienteRepository.deletarPorNomeOuIdade(nome, idade);
    }

}
