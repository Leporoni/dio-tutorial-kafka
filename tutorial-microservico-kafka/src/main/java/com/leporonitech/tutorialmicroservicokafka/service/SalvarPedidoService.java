package com.leporonitech.tutorialmicroservicokafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leporonitech.tutorialmicroservicokafka.data.PedidoData;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SalvarPedidoService {

    @KafkaListener(topics = "salvarPedido", groupId = "microServicoSalvaPedido")
    private void executar(ConsumerRecord<String, String> record) {
        log.info("Key = {}", record.key());
        log.info("Header = {}", record.headers());
        log.info("Partition = {}", record.partition());

        String strDados = record.value();

        ObjectMapper mapper = new ObjectMapper();
        PedidoData pedido;

        try {
            pedido = mapper.readValue(strDados, PedidoData.class);
        } catch (JsonProcessingException e) {
            log.error("Falha ao converter evento [dado={}}]", strDados, e);
            return;
        }

        log.info("Evento recebido = {}", pedido);

        //TODO Gravar no banco de dados
        //TODO Responder para a fila que o pedido foi salvo
    }

    private void gravar(PedidoData pedido) {
        //TODO Gravar no bando de dados
    }
}
