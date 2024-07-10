package com.personal.project.readsearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertDataInJson {
    private ObjectMapper mapper;

    public <T> T getData(String json, Class<T> model){
        this.mapper = new ObjectMapper();
        try {
            return this.mapper.readValue(json, model);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
