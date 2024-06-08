package com.example.projetospringapi.principal;

import com.example.projetospringapi.model.DadosMarca;
import com.example.projetospringapi.model.Veiculo;
import com.example.projetospringapi.service.ConsumoAPI;
import com.example.projetospringapi.service.ConverteDados;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.beans.JavaBean;
import java.util.*;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu()
    {
//        var json = consumo.obterDados(ENDERECO + "carros/marcas/22/modelos/9744/anos/2023-1");
//        Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
//        System.out.println(veiculo);

        System.out.println("***ESCOLHA O TIPO DE VEÍCULO***"
                            + "\n\n[1] - Carro\n[2] - Moto\n[3] - Caminhão");
        System.out.println("Opção: ");

        int op = leitura.nextInt();
        String tipoVeiculo = null;

        switch (op)
        {
            case 1:
            {
                tipoVeiculo = "carros";
                break;
            }
            case 2:
            {
                tipoVeiculo = "motos";
                break;
            }
            case 3:
            {
                tipoVeiculo = "caminhoes";
                break;
            }
            default:
            {
                System.out.println("Opção inválida!");
            }
        }

        var json = consumo.obterDados(ENDERECO + tipoVeiculo + "/marcas");

        Gson gson = new Gson();
        // Transformar o Json em uma lista de marcas
        List<DadosMarca> marcas = gson.fromJson(json, new TypeToken<List<DadosMarca>>() {}.getType());

        marcas.forEach(m -> {
                    System.out.println("Marca: " + m.nome() + " -- " + m.codigo());
                });


    }
}
