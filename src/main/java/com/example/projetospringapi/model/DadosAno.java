package com.example.projetospringapi.model;

import com.google.gson.annotations.SerializedName;

public record DadosAno(@SerializedName("codigo") String codigo,
                       @SerializedName("nome") String nome) {
    @Override
    public String codigo() {
        return codigo;
    }

    @Override
    public String nome() {
        return nome;
    }
}
