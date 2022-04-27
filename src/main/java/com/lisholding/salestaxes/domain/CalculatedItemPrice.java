package com.lisholding.salestaxes.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculatedItemPrice {

    private Double finalPrice;
    private Double totalTaxes;
}
