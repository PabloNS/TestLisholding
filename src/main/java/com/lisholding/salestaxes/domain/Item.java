package com.lisholding.salestaxes.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

    private String name;
    private double price;
    private boolean imported;
    private ItemType itemType;
}
