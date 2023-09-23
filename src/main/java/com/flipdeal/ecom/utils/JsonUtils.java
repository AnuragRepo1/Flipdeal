package com.flipdeal.ecom.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipdeal.ecom.dto.Product;

import java.util.List;

public class JsonUtils {

    public static <T> T parseJsonStringToDto(String jsonString, Class<T> dtoClass) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, dtoClass);
    }

}
