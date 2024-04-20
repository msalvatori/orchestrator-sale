package com.msbank.sale.core.output.service.sale.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msbank.sale.core.domain.Sale;


public record DataResponseDto(
        @JsonProperty("data")
        Sale sale
) {
}
