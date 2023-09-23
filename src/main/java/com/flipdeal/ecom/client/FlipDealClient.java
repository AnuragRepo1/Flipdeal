package com.flipdeal.ecom.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipdeal.ecom.configuration.AppConfig;
import com.flipdeal.ecom.constant.enums.Currency;
import com.flipdeal.ecom.dto.Product;
import com.flipdeal.ecom.utils.HttpCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class FlipDealClient {
    @Autowired
    private AppConfig appConfig;

    public List<Product> fetchProductDetails() throws IOException {
        String response = HttpCaller.get(appConfig.getCoverselfHost()+appConfig.getCoverselfProductDetailsUri(), null);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));
    }

    public  Map<String, Object> fetchCurrencyExchange() throws IOException {
        String response = HttpCaller.get(appConfig.getCoverselfHost()+appConfig.getCoverselfExchangeRateUri(), null);
        return (Map<String, Object>) new ObjectMapper().readValue(response, HashMap.class).get("rates");
    }

}
