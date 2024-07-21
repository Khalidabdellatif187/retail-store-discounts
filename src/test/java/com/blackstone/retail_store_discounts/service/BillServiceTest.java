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
        billDto.setTotalAmount(300.0);
        billDto.setNetAmount(255.0);

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

        BillDto billDto = new BillDto();
        billDto.setTotalAmount(100.0);
        billDto.setNetAmount(70.0);

        when(userService.findUserById(anyLong())).thenReturn(user);
        when(productService.findById(anyLong())).thenReturn(product);
        when(billMapper.map(any(Bill.class))).thenReturn(billDto);

        BillDto result = billService.calculateNetAmount(bill);

        assertEquals(70.0, result.getNetAmount());
        verify(userService).findUserById(anyLong());
        verify(productService).findById(anyLong());
        verify(billRepository).save(any(Bill.class));
        verify(billMapper).map(any(Bill.class));
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

        BillDto billDto = new BillDto();
        billDto.setTotalAmount(100.0);
        billDto.setNetAmount(90.0);

        when(userService.findUserById(anyLong())).thenReturn(user);
        when(productService.findById(anyLong())).thenReturn(product);
        when(billMapper.map(any(Bill.class))).thenReturn(billDto);

        BillDto result = billService.calculateNetAmount(bill);

        assertEquals(90.0, result.getNetAmount());
        verify(userService).findUserById(anyLong());
        verify(productService).findById(anyLong());
        verify(billRepository).save(any(Bill.class));
        verify(billMapper).map(any(Bill.class));
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

        BillDto billDto = new BillDto();
        billDto.setTotalAmount(100.0);
        billDto.setNetAmount(95.0);

        when(userService.findUserById(anyLong())).thenReturn(user);
        when(productService.findById(anyLong())).thenReturn(product);
        when(billMapper.map(any(Bill.class))).thenReturn(billDto);

        BillDto result = billService.calculateNetAmount(bill);

        assertEquals(95.0, result.getNetAmount());
        verify(userService).findUserById(anyLong());
        verify(productService).findById(anyLong());
        verify(billRepository).save(any(Bill.class));
        verify(billMapper).map(any(Bill.class));
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

        BillDto billDto = new BillDto();
        billDto.setTotalAmount(100.0);
        billDto.setNetAmount(100.0);

        when(userService.findUserById(anyLong())).thenReturn(user);
        when(productService.findById(anyLong())).thenReturn(groceriesProduct);
        when(billMapper.map(any(Bill.class))).thenReturn(billDto);

        BillDto result = billService.calculateNetAmount(bill);

        assertEquals(100.0, result.getNetAmount());
        verify(userService).findUserById(anyLong());
        verify(productService).findById(anyLong());
        verify(billRepository).save(any(Bill.class));
        verify(billMapper).map(any(Bill.class));
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

        BillDto billDto = new BillDto();
        billDto.setTotalAmount(300.0);
        billDto.setNetAmount(285.0);

        when(userService.findUserById(anyLong())).thenReturn(user);
        when(productService.findById(anyLong())).thenReturn(product1, product2);
        when(billMapper.map(any(Bill.class))).thenReturn(billDto);

        BillDto result = billService.calculateNetAmount(bill);

        assertEquals(285.0, result.getNetAmount());
        verify(userService).findUserById(anyLong());
        verify(productService, times(2)).findById(anyLong());
        verify(billRepository).save(any(Bill.class));
        verify(billMapper).map(any(Bill.class));
    }
}
