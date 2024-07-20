package com.blackstone.retail_store_discounts.service;

import com.blackstone.retail_store_discounts.dto.BillDto;
import com.blackstone.retail_store_discounts.model.Bill;

public interface BillService {


    BillDto calculateNetAmount(Bill bill);

}
