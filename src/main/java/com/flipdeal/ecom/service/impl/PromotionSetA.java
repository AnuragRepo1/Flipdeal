package com.flipdeal.ecom.service.impl;

import com.flipdeal.ecom.configuration.AppConfig;
import com.flipdeal.ecom.constant.FlipDealConstant;
import com.flipdeal.ecom.constant.enums.Origin;
import com.flipdeal.ecom.constant.enums.ProductCategory;
import com.flipdeal.ecom.dto.DiscountDetail;
import com.flipdeal.ecom.dto.Product;
import com.flipdeal.ecom.service.PromotionStrategy;
import com.flipdeal.ecom.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class PromotionSetA implements PromotionStrategy {
    @Autowired
    private AppConfig appConfig;
    @Override
    public DiscountDetail calculateDiscountForGivenPromotionSet(Product product) {
            DiscountDetail discountDetail = new DiscountDetail();
            double discount = 0.0;
            String promotioTag = "";
            if(Origin.AFRICA.name().equalsIgnoreCase(product.getOrigin())) {
                discount = Math.max(discount, Common.calculateDiscount(product.getPrice(), appConfig.getAfricanOriginPercentage()));
                promotioTag = "get 7% off";
            }
            if(Objects.nonNull(product.getRating()) && product.getRating() == 2){
                discount = Math.max(discount,  Common.calculateDiscount(product.getPrice(), appConfig.getEqualRatingPercentage()));
                promotioTag = "get 4% off";
            }else if(Objects.nonNull(product.getRating()) && product.getRating() < 2){
                discount = Math.max(discount, Common.calculateDiscount(product.getPrice(), appConfig.getBelowRatingPercentage()));
                promotioTag = "get 8% off";
            }
            if((ProductCategory.FURNISHING.name().equalsIgnoreCase(product.getCategory())  || ProductCategory.ELECTRONICS.name().equalsIgnoreCase(product.getCategory())) && (Objects.nonNull(product.getPrice()) && product.getPrice() >= 500)){
                discount = Math.max(discount, Double.valueOf(appConfig.getSpecialProductDiscount()));
                promotioTag = "get Rs 100 off";
            }
            discountDetail.setAmount(discount);
            discountDetail.setDiscountTag(promotioTag);
           return discountDetail;
    }
}
