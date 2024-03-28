package com.msbank.sale.adapter.output.service.sale.kafka.consumer;

import com.msbank.sale.adapter.output.service.sale.kafka.SaleMessage;
import com.msbank.sale.core.domain.enums.SaleEvent;
import com.msbank.sale.core.usecase.CancelSaleUseCase;
import com.msbank.sale.core.usecase.FindSaleByIdUseCase;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;

import static com.msbank.sale.core.constants.Constants.Kafka.SUCCESSFULLY_CONSUMED;
import static com.msbank.sale.core.constants.Constants.Sale.*;

@Component
public class CancelSaleConsumer {
    Logger LOGGER = LogManager.getLogger("Log4Core");
    @Autowired
    @Qualifier("${spring.kafka.consumer.bean-cancel}")
    private  ReactiveKafkaConsumerTemplate<String, SaleMessage> reactiveKafkaConsumerTemplate;
    @Autowired
    private  CancelSaleUseCase CancelSaleUseCase;
    @Autowired
    private  FindSaleByIdUseCase FindSaleByIdUseCase;
    @EventListener(ApplicationStartedEvent.class)
    public void receive() {
        reactiveKafkaConsumerTemplate
                .receiveAutoAck()
                .map(ConsumerRecord<String, SaleMessage>::value)
                .doOnNext(saleMessage -> {
                    LOGGER.info(SUCCESSFULLY_CONSUMED, SaleMessage.class.getSimpleName(), saleMessage);
                    if(SaleEvent.CANCEL_SALE.equals(saleMessage.getSaleEvent())) {
                        LOGGER.info(CANCELANDO_A_VENDA);
                        FindSaleByIdUseCase.find(saleMessage.getSale().getId())
                                .doOnSuccess(sale -> {
                                    CancelSaleUseCase.cancel(saleMessage.getSale());
                                }).block();
                        LOGGER.info(SALE, saleMessage.getSale());
                        LOGGER.info(VENDA_CANCELADA);
                    }

                })
                .subscribe();
    }
}
