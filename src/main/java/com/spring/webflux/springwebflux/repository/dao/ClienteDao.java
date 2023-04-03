package com.spring.webflux.springwebflux.repository.dao;

import com.spring.webflux.springwebflux.document.Cliente;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteDao extends ReactiveMongoRepository<Cliente, String> {

}
