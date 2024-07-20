package com.blackstone.retail_store_discounts.controller;

import com.blackstone.retail_store_discounts.dto.BillDto;
import com.blackstone.retail_store_discounts.model.Bill;
import com.blackstone.retail_store_discounts.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;


    @PostMapping
    public ResponseEntity<BillDto> calculateNetAmount(@RequestBody Bill bill){
        return new ResponseEntity<>(billService.calculateNetAmount(bill), HttpStatus.CREATED);
    }
}
