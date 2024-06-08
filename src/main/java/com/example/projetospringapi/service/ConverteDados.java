package com.example.projetospringapi.service;

import com.google.gson.Gson;

public class ConverteDados implements IConverteDados{

    private Gson gson = new Gson();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        return gson.fromJson(json, classe);
    }
}
