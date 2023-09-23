package com.flipdeal.ecom.service;

import com.flipdeal.ecom.dto.DiscountDetail;
import com.flipdeal.ecom.dto.Product;

import java.util.List;

public interface PromotionStrategy {
    DiscountDetail calculateDiscountForGivenPromotionSet(Product product);
}
