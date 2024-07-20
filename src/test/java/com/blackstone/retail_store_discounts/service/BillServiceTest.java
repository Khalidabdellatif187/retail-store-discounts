package com.blackstone.retail_store_discounts.service;

import com.blackstone.retail_store_discounts.dto.BillDto;
import com.blackstone.retail_store_discounts.enums.UserType;
import com.blackstone.retail_store_discounts.mapper.BillMapper;
import com.blackstone.retail_store_discounts.model.Bill;
import com.blackstone.retail_store_discounts.model.Category;
import com.blackstone.retail_store_discounts.model.Product;
import com.blackstone.retail_store_discounts.model.Users;
import com.blackstone.retail_store_discounts.repository.BillRepository;
import com.blackstone.retail_store_discounts.service_impl.BillServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class BillServiceTest {
    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @Mock
    private BillRepository billRepository;

    @Mock
    private BillMapper billMapper;

    @InjectMocks
    private BillServiceImpl billServiceImpl;

    private BillService billService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        billService = billServiceImpl;  // Assign the implementation to the interface
    }

    @Test
    void calculateNetAmount_shouldReturnBillDto() {
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

        List<Product> products = Arrays.asList(product1, product2);

        Bill bill = new Bill();
        bill.setUser(user);
        bill.setProducts(products);

        BillDto billDto = new BillDto();

        when(userService.findUserById(anyLong())).thenReturn(user);
        when(productService.findById(anyLong())).thenReturn(product1, product2);
        when(billMapper.map(any(Bill.class))).thenReturn(billDto);


        BillDto result = billService.calculateNetAmount(bill);


        assertEquals(billDto, result);
        verify(userService).findUserById(anyLong());
        verify(productService, times(2)).findById(anyLong());
        verify(billRepository).save(any(Bill.class));
        verify(billMapper).map(any(Bill.class));
    }
}
