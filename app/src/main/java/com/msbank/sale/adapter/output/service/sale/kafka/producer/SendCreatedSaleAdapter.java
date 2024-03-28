package com.msbank.sale.adapter.output.service.sale.kafka.producer;

import com.msbank.sale.adapter.output.service.sale.kafka.SaleMessage;
import com.msbank.sale.core.domain.Sale;
import com.msbank.sale.core.domain.enums.SaleEvent;
import com.msbank.sale.core.output.service.sale.dto.response.DataResponseDto;
import com.msbank.sale.core.output.service.sale.producer.SendCreate;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class SendCreatedSaleAdapter implements SendCreate {

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    @Autowired
    private ReactiveKafkaProducerTemplate<String, SaleMessage> reactiveKafkaProducerTemplate;
    @Override
    public Mono<DataResponseDto> create(Sale sale, SaleEvent event) {
        var saleMessage = SaleMessage.builder().saleEvent(event).sale(sale).build();

        log.info("send to topic={}, {}={},", topic, saleMessage);
        reactiveKafkaProducerTemplate.send(topic, saleMessage)
                .doOnSuccess(senderResult -> log.info("sent {} offset : {}",
                        sale,
                        senderResult.recordMetadata().offset()))
                .subscribe();
       return Mono.just(DataResponseDto.builder()
                .sale(sale)
                .build());
    }
}
