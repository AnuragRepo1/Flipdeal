package com.flipdeal.ecom.utils;

import com.flipdeal.ecom.constant.enums.Currency;
import com.flipdeal.ecom.dto.Product;

import java.util.List;
import java.util.Map;

public class Common {
    public static double calculateDiscount(int basePrice, int discountPercentage){
        return (basePrice*discountPercentage)/100;
    }

    public static List<Product> changeCurrenyToINR(List<Product> products, Map<String, Object> currencyExchange) {
        for (Product product : products) {
            if (!Currency.INR.name().equalsIgnoreCase(product.getCurrency())) {
                product.setPrice((int) (calculatePriceInINR(product, currencyExchange)));
            }
        }
        return products;
    }
    private static double calculatePriceInINR(Product product, Map<String, Object> currencyExchange) {
        return product.getPrice() * (double) currencyExchange.get(product.getCurrency());
    }
}
