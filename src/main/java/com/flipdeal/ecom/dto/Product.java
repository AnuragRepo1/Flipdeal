package com.flipdeal.ecom.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private String category;
    private int inventory;
    private double rating;
    private String currency;
    private int price;
    private String origin;
    private String product;
    private String arrival;
    private DiscountDetail discount;
}
