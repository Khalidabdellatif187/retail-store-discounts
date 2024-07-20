package com.blackstone.retail_store_discounts.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillDto {

    private Double totalAmount;
    private Double netAmount;

}
