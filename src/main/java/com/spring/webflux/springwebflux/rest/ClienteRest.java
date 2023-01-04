package com.spring.webflux.springwebflux.rest;

import com.spring.webflux.springwebflux.component.ImgComponent;
import com.spring.webflux.springwebflux.document.Cliente;
import com.spring.webflux.springwebflux.service.ClienteServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cliente/v1")
@CrossOrigin(origins = "*")
public class ClienteRest {

    private ClienteServiceImpl clienteService;

    private ImgComponent imgComponent;

    @Autowired
    public ClienteRest(ClienteServiceImpl clienteService, ImgComponent imgComponent) {
        this.clienteService = clienteService;
        this.imgComponent = imgComponent;
    }

    @Async
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    protected Mono<ResponseEntity<?>> registrarCliente(@RequestBody @Valid final Cliente cliente) {
        cliente.setFoto(imgComponent.defaultImg());
        return this.clienteService.save(cliente).flatMap(c -> Mono.just(ResponseEntity.ok()
                .body("Cliente registrado correctamente")));
    }

    @Async
    @PutMapping(value = "/img/{id_cliente}", consumes = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    protected Mono<ResponseEntity<?>> cambiarImagenCliente(@PathVariable("id_cliente") final String idCliente, @RequestPart final MultipartFile file) {
        return this.clienteService.findById(idCliente).flatMap(c -> {
            c.setFoto(imgComponent.save(file));
            return this.clienteService.save(c).flatMap(cc -> Mono.just(ResponseEntity.ok()
                    .body(cc.getFoto())));
        });
    }

    @Async
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    protected Mono<ResponseEntity<Flux<Cliente>>> listarClientes() {
        return Mono.just(ResponseEntity.ok()
                .body(this.clienteService.findAll()));
    }

    @Async
    @GetMapping(value = "/img/{img}")
    protected ResponseEntity<?> imagenCliente(@PathVariable("img") final String img) {
        return ResponseEntity.ok().body(this.imgComponent.obtenerFoto(img));
    }
}
