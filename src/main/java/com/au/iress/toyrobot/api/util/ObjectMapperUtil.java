package com.au.iress.toyrobot.api.util;

import com.au.iress.toyrobot.api.response.ToyRobotResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperUtil {
    public static ToyRobotResponse convertJSONStringToObject(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ToyRobotResponse response = mapper.readValue(json, ToyRobotResponse.class);
        return response;
    }
}
