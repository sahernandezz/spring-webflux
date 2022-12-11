package com.spring.webflux.springwebflux.repository;

import com.spring.webflux.springwebflux.document.Cliente;
import com.spring.webflux.springwebflux.repository.dao.ClienteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ClienteRepository {

    private ClienteDao clienteDao;

    @Autowired
    public ClienteRepository(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public Flux<Cliente> findAll() {
        return this.clienteDao.findAll();
    }

    public Mono<Cliente> findById(final String id) {
        return this.clienteDao.findById(id);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class, timeout = 30, noRollbackFor = RuntimeException.class)
    public Mono<Cliente> save(final Cliente cliente) {
        return this.clienteDao.save(cliente);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class, timeout = 30, noRollbackFor = RuntimeException.class)
    public Mono<Void> delete(final Cliente cliente) {
        return this.clienteDao.delete(cliente);
    }
}
