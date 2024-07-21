package com.blackstone.retail_store_discounts.service_impl;

import com.blackstone.retail_store_discounts.constants.Constants;
import com.blackstone.retail_store_discounts.dto.BillDto;
import com.blackstone.retail_store_discounts.enums.UserType;
import com.blackstone.retail_store_discounts.model.Bill;
import com.blackstone.retail_store_discounts.model.Product;
import com.blackstone.retail_store_discounts.model.Users;
import com.blackstone.retail_store_discounts.repository.BillRepository;
import com.blackstone.retail_store_discounts.service.BillService;
import com.blackstone.retail_store_discounts.service.ProductService;
import com.blackstone.retail_store_discounts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public BillDto calculateNetAmount(Bill bill) {
        Users user = getUser(bill.getUser().getId());
        List<Product> products = getProductsByIds(bill.getProducts());
        double totalAmountBeforeDiscounts = calculateTotalAmount(products);
        double discountPercentage = getDiscountPercentage(user);
        double totalPercentageDiscountAmount = calculateTotalPercentageDiscount(products, discountPercentage);
        double additionalDiscountForOneHundredUsd = calculateAdditionalDiscount(totalAmountBeforeDiscounts - totalPercentageDiscountAmount);
        double netAmountAfterDiscounts = totalAmountBeforeDiscounts - totalPercentageDiscountAmount - additionalDiscountForOneHundredUsd;
        String productIds = getProductIdsAsString(products);
        updateBill(bill, totalAmountBeforeDiscounts, netAmountAfterDiscounts, productIds);
        billRepository.save(bill);
        BillDto billDto = map(bill);
        return billDto;
    }

    private BillDto map(Bill bill){
        BillDto billDto = new BillDto();
        billDto.setTotalAmount(bill.getTotalAmount());
        billDto.setNetAmount(bill.getNetAmount());
        return billDto;
    }


    private Users getUser(Long userId) {
        return userService.findUserById(userId);
    }

    private List<Product> getProductsByIds(List<Product> products) {
        return products.stream()
                .map(product -> productService.findById(product.getId()))
                .collect(Collectors.toList());
    }

    public double calculateTotalAmount(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    private double getDiscountPercentage(Users user) {
        if (user.getUserType().equals(UserType.EMPLOYEE)) {
            return Constants.EMPLOYEE_DISCOUNT;
        } else if (user.getUserType().equals(UserType.AFFILIATE)) {
            return Constants.AFFILIATE_DISCOUNT;
        } else if (user.getUserType().equals(UserType.CUSTOMER) &&
                user.getJoinDate().isBefore(LocalDate.now().minusYears(2))) {
            return Constants.CUSTOMER_DISCOUNT;
        } else {
            return 0.0;
        }
    }

    private double calculateTotalPercentageDiscount(List<Product> products, double discountPercentage) {
        return products.stream()
                .filter(product -> !product.getCategory().getName().equals("groceries"))
                .mapToDouble(product -> product.getPrice() * discountPercentage)
                .sum();
    }

    private double calculateAdditionalDiscount(double amount) {
        return (int) (amount / 100) * 5;
    }

    private String getProductIdsAsString(List<Product> products) {
        return products.stream()
                .map(product -> product.getId().toString())
                .collect(Collectors.joining(","));
    }

    private void updateBill(Bill bill, double totalAmount, double netAmount, String productIds) {
        bill.setTotalAmount(totalAmount);
        bill.setNetAmount(netAmount);
        bill.setProductIds(productIds);
    }
}
