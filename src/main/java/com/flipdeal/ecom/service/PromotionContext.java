package com.flipdeal.ecom.service;

import com.flipdeal.ecom.dto.DiscountDetail;
import com.flipdeal.ecom.dto.Product;

public class PromotionContext {
    private PromotionStrategy promotionStrategy;

    public PromotionContext(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public DiscountDetail applyPromotion(Product product) {
        return promotionStrategy.calculateDiscountForGivenPromotionSet(product);
    }
}
