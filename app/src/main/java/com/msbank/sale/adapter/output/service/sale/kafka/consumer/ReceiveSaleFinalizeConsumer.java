package com.msbank.sale.adapter.output.service.sale.kafka.consumer;

import com.msbank.sale.adapter.output.service.sale.kafka.SaleMessage;
import com.msbank.sale.core.domain.enums.SaleEvent;
import com.msbank.sale.core.usecase.FinalizeSaleUseCase;
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
import static com.msbank.sale.core.constants.Constants.Sale.FINALIZANDO_A_VENDA;
import static com.msbank.sale.core.constants.Constants.Sale.VENDA_FINALIZADA_C_SUCESSO;

@Component
public class ReceiveSaleFinalizeConsumer {


    Logger LOGGER = LogManager.getLogger("Log4Core");

    @Autowired
    @Qualifier("${spring.kafka.consumer.bean-finalize}")
    private  ReactiveKafkaConsumerTemplate<String, SaleMessage> reactiveKafkaConsumerTemplate;

    @Autowired
    private  FinalizeSaleUseCase finalizeSale;

    @Autowired
    private  FindSaleByIdUseCase findSaleByIdUseCase;

    @EventListener(ApplicationStartedEvent.class)
    public void receive() {
         reactiveKafkaConsumerTemplate
                .receiveAutoAck()
                .map(ConsumerRecord<String, SaleMessage>::value)
                .doOnNext(saleMessage -> {
                    if(SaleEvent.FINALIZE_SALE.equals(saleMessage.getSaleEvent())) {
                        findSaleByIdUseCase.execute(saleMessage.getSale().getId())
                                .doOnSuccess(sale -> {
                                    finalizeSale.execute(saleMessage.getSale());
                                }).block();;
                        LOGGER.info(FINALIZANDO_A_VENDA);
                        LOGGER.info(VENDA_FINALIZADA_C_SUCESSO);
                    }
                    LOGGER.info(SUCCESSFULLY_CONSUMED, SaleMessage.class.getSimpleName(), saleMessage);

                })
                 .subscribe();

    }
}
