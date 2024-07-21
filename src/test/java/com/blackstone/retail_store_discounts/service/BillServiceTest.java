package com.blackstone.retail_store_discounts.service;

import com.blackstone.retail_store_discounts.dto.BillDto;
import com.blackstone.retail_store_discounts.enums.UserType;
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

    @InjectMocks
    private BillServiceImpl billServiceImpl;

    private BillService billService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        billService = billServiceImpl;  // Assign the implementation to the interface
    }

    @Test
    void calculateNetAmount_shouldApplyEmployeeDiscount() {
        Users user = new Users();
        user.setId(1L);
        user.setUserType(UserType.EMPLOYEE);
        user.setJoinDate(LocalDate.now().minusYears(3));

        Category category = new Category();
        category.setId(1L);
        category.setName("electronics");

        Product product = new Product();
        product.setId(1L);
        product.setPrice(100.0);
        product.setCategory(category);

        List<Product> products = Arrays.asList(product);

        Bill bill = new Bill();
        bill.setUser(user);
        bill.setProducts(products);

        when(userService.findUserById(anyLong())).thenReturn(user);
        when(productService.findById(anyLong())).thenReturn(product);

        BillDto result = billService.calculateNetAmount(bill);

        double expectedTotalAmount = 100.0;
        double expectedPercentageDiscount = expectedTotalAmount * 0.30; // 30% discount for employee
        double amountAfterPercentageDiscount = expectedTotalAmount - expectedPercentageDiscount;
        double expectedAdditionalDiscount = (int)(amountAfterPercentageDiscount / 100) * 5; // $5 discount for each $100 after percentage discount
        double expectedNetAmount = amountAfterPercentageDiscount - expectedAdditionalDiscount;

        assertEquals(expectedNetAmount, result.getNetAmount());
        verify(userService).findUserById(anyLong());
        verify(productService).findById(anyLong());
        verify(billRepository).save(any(Bill.class));
    }

    @Test
    void calculateNetAmount_shouldApplyAffiliateDiscount() {
        Users user = new Users();
        user.setId(1L);
        user.setUserType(UserType.AFFILIATE);
        user.setJoinDate(LocalDate.now().minusYears(3));

        Category category = new Category();
        category.setId(1L);
        category.setName("electronics");

        Product product = new Product();
        product.setId(1L);
        product.setPrice(100.0);
        product.setCategory(category);

        List<Product> products = Arrays.asList(product);

        Bill bill = new Bill();
        bill.setUser(user);
        bill.setProducts(products);

        when(userService.findUserById(anyLong())).thenReturn(user);
        when(productService.findById(anyLong())).thenReturn(product);

        BillDto result = billService.calculateNetAmount(bill);

        double expectedTotalAmount = 100.0;
        double expectedPercentageDiscount = expectedTotalAmount * 0.10; // 10% discount for affiliate
        double amountAfterPercentageDiscount = expectedTotalAmount - expectedPercentageDiscount;
        double expectedAdditionalDiscount = (int)(amountAfterPercentageDiscount / 100) * 5; // $5 discount for each $100 after percentage discount
        double expectedNetAmount = amountAfterPercentageDiscount - expectedAdditionalDiscount;

        assertEquals(expectedNetAmount, result.getNetAmount());
        verify(userService).findUserById(anyLong());
        verify(productService).findById(anyLong());
        verify(billRepository).save(any(Bill.class));
    }

    @Test
    void calculateNetAmount_shouldApplyCustomerDiscount() {
        Users user = new Users();
        user.setId(1L);
        user.setUserType(UserType.CUSTOMER);
        user.setJoinDate(LocalDate.now().minusYears(3));

        Category category = new Category();
        category.setId(1L);
        category.setName("electronics");

        Product product = new Product();
        product.setId(1L);
        product.setPrice(100.0);
        product.setCategory(category);

        List<Product> products = Arrays.asList(product);

        Bill bill = new Bill();
        bill.setUser(user);
        bill.setProducts(products);

        when(userService.findUserById(anyLong())).thenReturn(user);
        when(productService.findById(anyLong())).thenReturn(product);

        BillDto result = billService.calculateNetAmount(bill);

        double expectedTotalAmount = 100.0;
        double expectedPercentageDiscount = expectedTotalAmount * 0.05; // 5% discount for customer over 2 years
        double amountAfterPercentageDiscount = expectedTotalAmount - expectedPercentageDiscount;
        double expectedAdditionalDiscount = (int)(amountAfterPercentageDiscount / 100) * 5; // $5 discount for each $100 after percentage discount
        double expectedNetAmount = amountAfterPercentageDiscount - expectedAdditionalDiscount;

        assertEquals(expectedNetAmount, result.getNetAmount());
        verify(userService).findUserById(anyLong());
        verify(productService).findById(anyLong());
        verify(billRepository).save(any(Bill.class));
    }

    @Test
    void calculateNetAmount_shouldNotApplyDiscountOnGroceries() {
        Users user = new Users();
        user.setId(1L);
        user.setUserType(UserType.EMPLOYEE);
        user.setJoinDate(LocalDate.now().minusYears(3));

        Category groceriesCategory = new Category();
        groceriesCategory.setId(1L);
        groceriesCategory.setName("groceries");

        Product groceriesProduct = new Product();
        groceriesProduct.setId(1L);
        groceriesProduct.setPrice(100.0);
        groceriesProduct.setCategory(groceriesCategory);

        List<Product> products = Arrays.asList(groceriesProduct);

        Bill bill = new Bill();
        bill.setUser(user);
        bill.setProducts(products);

        when(userService.findUserById(anyLong())).thenReturn(user);
        when(productService.findById(anyLong())).thenReturn(groceriesProduct);

        BillDto result = billService.calculateNetAmount(bill);

        double expectedTotalAmount = 100.0;
        double expectedPercentageDiscount = 0.0; // No discount for groceries
        double amountAfterPercentageDiscount = expectedTotalAmount - expectedPercentageDiscount;
        double expectedAdditionalDiscount = (int)(amountAfterPercentageDiscount / 100) * 5;
        double expectedNetAmount = amountAfterPercentageDiscount - expectedAdditionalDiscount;

        assertEquals(expectedNetAmount, result.getNetAmount());
        verify(userService).findUserById(anyLong());
        verify(productService).findById(anyLong());
        verify(billRepository).save(any(Bill.class));
    }


    @Test
    void calculateNetAmount_shouldApplyAdditionalDiscountForEveryHundredUsd() {
        Users user = new Users();
        user.setId(1L);
        user.setUserType(UserType.CUSTOMER);
        user.setJoinDate(LocalDate.now().minusYears(3));

        Category category = new Category();
        category.setId(1L);
        category.setName("electronics");

        Product product1 = new Product();
        product1.setId(1L);
        product1.setPrice(150.0);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setPrice(150.0);
        product2.setCategory(category);

        List<Product> products = Arrays.asList(product1, product2);

        Bill bill = new Bill();
        bill.setUser(user);
        bill.setProducts(products);

        when(userService.findUserById(anyLong())).thenReturn(user);
        when(productService.findById(anyLong())).thenReturn(product1, product2);

        BillDto result = billService.calculateNetAmount(bill);

        double expectedTotalAmount = 300.0;
        double expectedPercentageDiscount = expectedTotalAmount * 0.05; // 5% discount for customer over 2 years
        double amountAfterPercentageDiscount = expectedTotalAmount - expectedPercentageDiscount;
        double expectedAdditionalDiscount = (int)(amountAfterPercentageDiscount / 100) * 5;
        double expectedNetAmount = amountAfterPercentageDiscount - expectedAdditionalDiscount;

        assertEquals(expectedNetAmount, result.getNetAmount());
        verify(userService).findUserById(anyLong());
        verify(productService, times(2)).findById(anyLong());
        verify(billRepository).save(any(Bill.class));
    }
}
