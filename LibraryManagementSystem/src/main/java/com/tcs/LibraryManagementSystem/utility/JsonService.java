package com.tcs.LibraryManagementSystem.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

    private final ObjectMapper objectMapper;

    public JsonService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JsonNode convertStringToUser(String jsonString) throws Exception {
        // Convert JSON string to User object
        return objectMapper.readTree(jsonString);
    }
}
