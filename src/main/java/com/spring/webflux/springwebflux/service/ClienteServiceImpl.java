package com.spring.webflux.springwebflux.service;

import com.spring.webflux.springwebflux.document.Cliente;
import com.spring.webflux.springwebflux.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(final ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Flux<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    @Override
    public Mono<Cliente> findById(String id) {
        return this.clienteRepository.findById(id);
    }

    @Override
    public Mono<Cliente> save(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    @Override
    public Mono<Void> delete(Cliente cliente) {
        return this.clienteRepository.delete(cliente);
    }
}
