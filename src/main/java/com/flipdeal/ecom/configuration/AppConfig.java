package com.flipdeal.ecom.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfig {
    @Value("${coverself.host}")
    private String coverselfHost;

    @Value("${coverself.product.details.uri}")
    private String coverselfProductDetailsUri;

    @Value("${coverself.exchange.rate.uri}")
    private String coverselfExchangeRateUri;

    @Value("${inventory.count}")
    private int inventoryCount;

    @Value("${inventory.count.percentage}")
    private int inventoryCountPercentage;

    @Value("${new.arrival.percentage}")
    private int newArrivalPercentage;

    @Value("${african.origin.percentage}")
    private int africanOriginPercentage;

    @Value("${below.rating.percentage}")
    private int belowRatingPercentage;

    @Value("${equal.rating.percentage}")
    private int equalRatingPercentage;

    @Value("${special.product.discount}")
    private int specialProductDiscount;


}
