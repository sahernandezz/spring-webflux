package com.spring.webflux.springwebflux.service;

import com.spring.webflux.springwebflux.document.Cliente;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {
    Flux<Cliente> findAll();

    Mono<Cliente> findById(final String id);

    Mono<Cliente> save(final Cliente cliente);

    Mono<Void> delete(final Cliente cliente);
}
