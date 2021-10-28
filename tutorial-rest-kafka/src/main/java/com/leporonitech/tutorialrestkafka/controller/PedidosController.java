package com.leporonitech.tutorialrestkafka.controller;

import com.leporonitech.tutorialrestkafka.data.PedidoData;
import com.leporonitech.tutorialrestkafka.service.RegistraEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PedidosController {

    private final RegistraEventoService service;

    @PostMapping(path = "/api/salva-pedido")
    public ResponseEntity<String> salvarPedido(@RequestBody PedidoData pedido) {
        service.adicionarEvento("salvarPedido", pedido);
        return ResponseEntity.ok("Sucesso");
    }
}
