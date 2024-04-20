package com.msbank.sale.config.kafka;

import com.msbank.sale.adapter.output.service.sale.kafka.SaleMessage;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.msbank.sale.config.kafka.CustomDeserializer.CONFIG_VALUE_CLASS;
import static com.msbank.sale.core.constants.Constants.Kafka.SALE_FINALIZE;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;

@EnableKafka
@Configuration
public class SaleKafkaConsumerFinalizeConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap;

    @Bean(name = "${spring.kafka.consumer.bean-receiver-finalize}")
    public ReceiverOptions<String, SaleMessage> kafkaReceiverOptions(@Value(value = "${spring.kafka.consumer.topic}") String topic) {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        props.put(GROUP_ID_CONFIG, SALE_FINALIZE);
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class.getName());
        props.put(CONFIG_VALUE_CLASS, SaleMessage.class.getName());
        props.put(AUTO_OFFSET_RESET_CONFIG, "earliest");
        ReceiverOptions<String, SaleMessage> basicReceiverOptions = ReceiverOptions.create(props);
        return basicReceiverOptions.subscription(Collections.singletonList(topic));
    }

    @Bean(name = "${spring.kafka.consumer.bean-finalize}")
    public ReactiveKafkaConsumerTemplate<String, SaleMessage> reactiveKafkaConsumerTemplate(@Qualifier("${spring.kafka.consumer.bean-receiver-finalize}") ReceiverOptions<String, SaleMessage> kafkaReceiverOptions) {
        return new ReactiveKafkaConsumerTemplate<String, SaleMessage>(kafkaReceiverOptions);
    }

}
