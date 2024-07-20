package com.blackstone.retail_store_discounts.mapper;

import com.blackstone.retail_store_discounts.dto.BillDto;
import com.blackstone.retail_store_discounts.model.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BillMapper{

    @Mapping(target = "totalAmount",source = "totalAmount")
    @Mapping(target = "netAmount" ,source = "netAmount")
    BillDto map(Bill bill);
}
