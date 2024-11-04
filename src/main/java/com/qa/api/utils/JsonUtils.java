package com.qa.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class JsonUtils {

    private static ObjectMapper obeObjectMapper = new ObjectMapper();

    public static <T> T deserialize(Response response,Class<T> targetClass){
        try {
            return obeObjectMapper.readValue(response.getBody().asString(),targetClass);
        } catch (Exception e) {
            throw new RuntimeException("Deserialization is failed...." + targetClass.getName());
        }
    }
}
