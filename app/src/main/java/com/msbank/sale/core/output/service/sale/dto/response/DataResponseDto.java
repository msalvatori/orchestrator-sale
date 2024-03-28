package com.msbank.sale.core.output.service.sale.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msbank.sale.core.domain.Sale;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DataResponseDto {

    @JsonProperty("data")
    private Sale sale;

}
