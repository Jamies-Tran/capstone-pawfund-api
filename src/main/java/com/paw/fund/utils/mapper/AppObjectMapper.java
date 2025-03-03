package com.paw.fund.utils.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppObjectMapper {
    public static ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public static String convertDataToJsonString(Object data) {
        try {
            return objectMapper().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("[{}-convertDataToJsonString] có lỗi xảy ra: {} ", AppObjectMapper.class.getSimpleName(), e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
