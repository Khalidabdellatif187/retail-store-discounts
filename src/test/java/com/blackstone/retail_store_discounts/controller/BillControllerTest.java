package com.blackstone.retail_store_discounts.controller;


import com.blackstone.retail_store_discounts.dto.BillDto;
import com.blackstone.retail_store_discounts.enums.UserType;
import com.blackstone.retail_store_discounts.model.Bill;
import com.blackstone.retail_store_discounts.model.Category;
import com.blackstone.retail_store_discounts.model.Product;
import com.blackstone.retail_store_discounts.model.Users;
import com.blackstone.retail_store_discounts.service.BillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;

@SpringBootTest
@AutoConfigureMockMvc
public class BillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BillService billService;

    @Test
    void calculateNetAmount_shouldReturnCreatedBillDto() throws Exception {
        Users user = new Users();
        user.setId(1L);
        user.setUserType(UserType.EMPLOYEE);
        user.setJoinDate(LocalDate.now().minusYears(3));

        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("electronics");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("groceries");

        Product product1 = new Product();
        product1.setId(1L);
        product1.setPrice(100.0);
        product1.setCategory(category1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setPrice(200.0);
        product2.setCategory(category2);

        Bill bill = new Bill();
        bill.setUser(user);
        bill.setProducts(Arrays.asList(product1, product2));

        BillDto billDto = new BillDto();
        billDto.setTotalAmount(300.0);
        billDto.setNetAmount(255.0);

        when(billService.calculateNetAmount(any(Bill.class))).thenReturn(billDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/bill")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(bill)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalAmount").value(300.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.netAmount").value(255.0));
    }

}
