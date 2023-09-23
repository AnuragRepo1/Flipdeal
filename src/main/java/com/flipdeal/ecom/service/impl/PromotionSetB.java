package com.flipdeal.ecom.service.impl;

import com.flipdeal.ecom.configuration.AppConfig;
import com.flipdeal.ecom.constant.enums.Arrival;
import com.flipdeal.ecom.dto.DiscountDetail;
import com.flipdeal.ecom.dto.Product;
import com.flipdeal.ecom.service.PromotionStrategy;
import com.flipdeal.ecom.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionSetB implements PromotionStrategy {
    @Autowired
    private AppConfig appConfig;
    @Override
    public DiscountDetail calculateDiscountForGivenPromotionSet(Product product) {
        DiscountDetail discountDetail = new DiscountDetail();
        double discount = 0.0;
        String promotioTag = "";
        if(appConfig.getInventoryCount() < product.getInventory()  ) {
            discount = Math.max(discount, Common.calculateDiscount(product.getPrice(), appConfig.getInventoryCountPercentage()));
            promotioTag = "get 12% off";
        }
        if(Arrival.NEW.name().equalsIgnoreCase(product.getArrival())){
            discount = Math.max(discount, Common.calculateDiscount(product.getPrice(), appConfig.getNewArrivalPercentage()));
            promotioTag = "get 7% off";
        }
        discountDetail.setAmount(discount);
        discountDetail.setDiscountTag(promotioTag);
        return discountDetail;
    }

}
