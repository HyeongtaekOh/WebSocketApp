package com.example.websocketapp.utils;

import com.example.websocketapp.domain.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

    private static ObjectMapper mapper = new ObjectMapper();
    public static String getJsonString(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static Message getObject(String json) throws JsonProcessingException {
        return mapper.readValue(json, Message.class);
    }
}
