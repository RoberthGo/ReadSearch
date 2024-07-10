package com.personal.project.readsearch.service;

public interface IConvertData {
    public <T> T getData(String json, Class<T> model);
}
