package com.msbank.sale.adapter.input.api.sale.v1.validator;

import com.msbank.sale.core.error.exception.BadRequestException;
import io.vavr.control.Try;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.stream.Collectors;


@Validated
public interface HandlerValidator {

    Logger LOGGER = LogManager.getLogger("Log4Core");

    default <T> Mono<T> bodyValidation(T body) {
        return Mono.just(Optional.ofNullable(body)
                .orElseThrow(() -> {
                    LOGGER.error("Request body é obrigatório");
                    return new BadRequestException();
                }));
    }
    default <T> Mono<Object> fieldsValidation(@NotNull final T body) {
        return Try.withResources(Validation::buildDefaultValidatorFactory)
                .of(ValidatorFactory::getValidator)
                .map(validator -> validator.validate(body))
                .filter(validator -> !validator.isEmpty())
                .map(validator -> {
                    LOGGER.error("Falha na validação dos campos: {}",
                            validator.stream()
                                    .map(ConstraintViolation::getMessage)
                                    .collect(Collectors.joining(", ")));

                    return Mono.error(new BadRequestException());
                })
                .getOrElse(Mono.empty());
    }
}