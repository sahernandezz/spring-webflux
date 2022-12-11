package com.spring.webflux.springwebflux.repository.dao;

import com.spring.webflux.springwebflux.document.Cliente;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClienteDao extends ReactiveMongoRepository<Cliente, String> {

}
