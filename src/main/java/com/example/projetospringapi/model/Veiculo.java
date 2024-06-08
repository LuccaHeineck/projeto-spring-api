package com.example.projetospringapi.model;

import com.google.gson.annotations.SerializedName;

public record Veiculo(@SerializedName("TipoVeiculo") String tipo,
                             @SerializedName("Valor") String valor,
                             @SerializedName("Marca") String marca,
                             @SerializedName("Modelo") String modelo,
                             @SerializedName("AnoModelo") Integer ano,
                             @SerializedName("Combustivel") String combustivel,
                             @SerializedName("CodigoFipe") String codigoFipe,
                             @SerializedName("MesReferencia") String mesReferencia,
                             @SerializedName("SiglaCombustivel") String siglaCombustivel) {
}
