package com.flipdeal.ecom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipdeal.ecom.client.FlipDealClient;
import com.flipdeal.ecom.constant.enums.PromotionSet;
import com.flipdeal.ecom.dto.DiscountDetail;
import com.flipdeal.ecom.dto.Product;
import com.flipdeal.ecom.service.PromotionContext;
import com.flipdeal.ecom.service.PromotionStrategy;
import com.flipdeal.ecom.service.impl.PromotionSetA;
import com.flipdeal.ecom.service.impl.PromotionSetB;
import com.flipdeal.ecom.utils.Common;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class EcomApplication {
	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(EcomApplication.class, args);
		SpringApplication.run(EcomApplication.class, args);
		FlipDealClient flipDealClient = context.getBean(FlipDealClient.class);
		try {
			if (args.length == 0){
				throw new Exception("No argument");
			}
			List<Product> products = flipDealClient.fetchProductDetails();
			Map<String, Object> currencyExchange = flipDealClient.fetchCurrencyExchange();
			List<Product> updatedProducts = Common.changeCurrenyToINR(products, currencyExchange);
			PromotionStrategy promotionStrategy;
			PromotionSet promotionSet = PromotionSet.valueOf(args[0]);
			switch (promotionSet) {
				case promotionSetA:
					promotionStrategy = context.getBean(PromotionSetA.class);
					break;
				case promotionSetB:
					promotionStrategy = context.getBean(PromotionSetB.class);
					break;
				default:
					throw new IllegalArgumentException("No promotionSet found");
			}
			for (Product product : updatedProducts) {
				PromotionContext promotionContext = new PromotionContext(promotionStrategy);
				DiscountDetail discount = promotionContext.applyPromotion(product);
				product.setDiscount(discount);
				System.out.println("Product Category "+ product.getCategory()+" Product price "+product.getPrice()+" Discoumnt "+discount.getAmount()+" Discount Tag "+ discount.getDiscountTag());
			}
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(new File("output.json"), products);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

