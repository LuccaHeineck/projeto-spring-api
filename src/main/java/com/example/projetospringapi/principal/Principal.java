package com.example.projetospringapi.principal;

import com.example.projetospringapi.model.*;
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
        leitura.nextLine();

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

        // ------------------ MARCA -------------------------

        var json = consumo.obterDados(ENDERECO + tipoVeiculo + "/marcas");

        Gson gson = new Gson();
        List<DadosMarca> marcas = gson.fromJson(json, new TypeToken<List<DadosMarca>>() {}.getType());

        marcas.forEach(m -> {
                    System.out.println("Marca: " + m.nome() + " -- " + m.codigo());
                });

//        System.out.println("\nEscolha uma marca pelo código: ");
//        var marcaBuscada = leitura.nextInt();
//
//        marcas.forEach(m ->{
//                if (marcaBuscada == Integer.parseInt(m.codigo()))
//                {
//                    System.out.println("\nMarca escolhida: " + m.nome());
//                }
//        });

        boolean marcaEncontrada = false;
        int marcaBuscada = 0;
        while (!marcaEncontrada) {
            System.out.println("\nEscolha uma marca pelo código (ou digite 0 para sair): ");
            marcaBuscada = leitura.nextInt();
            leitura.nextLine();

            if (marcaBuscada == 0) {
                System.out.println("Saindo...");
                return;
            }

            for (DadosMarca marca : marcas) {
                if (marcaBuscada == Integer.parseInt(marca.codigo())) {
                    System.out.println("\nMarca escolhida: " + marca.nome());
                    marcaEncontrada = true;
                    break;
                }
            }

            if (!marcaEncontrada) {
                System.out.println("Código inválido! Tente novamente.");
            }
        }

        // ----------------------- MODELO -------------------------------

        json = consumo.obterDados(ENDERECO + tipoVeiculo + "/marcas/" + marcaBuscada + "/modelos");

        gson = new Gson();
        EstruturaModelos estruturaModelos = gson.fromJson(json, EstruturaModelos.class);
        List<DadosModelo> modelos = estruturaModelos.getModelos();

        modelos.forEach(m -> {
            System.out.println("Modelo: " + m.nome() + " --- cód: " + m.codigo());
        });

        boolean modeloEncontrado = false;
        int modeloBuscado = 0;

        while (!modeloEncontrado) {
            System.out.println("\nEscolha um modelo pelo código (ou digite 0 para sair): ");
            modeloBuscado = leitura.nextInt();
            leitura.nextLine();

            if (modeloBuscado == 0) {
                System.out.println("Saindo...");
                return;
            }

            for (DadosModelo modelo : modelos) {
                if (modeloBuscado == Integer.parseInt(modelo.codigo())) {
                    System.out.println("\nModelo escolhido: " + modelo.nome());
                    modeloEncontrado = true;
                    break;
                }
            }

            if (!modeloEncontrado) {
                System.out.println("Código inválido! Tente novamente.");
            }
        }

        // ------------------ ANO -----------------------------

        json = consumo.obterDados(ENDERECO + tipoVeiculo + "/marcas/" + marcaBuscada + "/modelos/" + modeloBuscado + "/anos");
        gson = new Gson();
        List<DadosAno> anos = gson.fromJson(json, new TypeToken<List<DadosAno>>() {}.getType());

        anos.forEach(m -> {
            System.out.println("Ano: " + m.nome() + " -- " + m.codigo());
        });

        boolean anoEncontrado = false;
        String anoBuscado = "";

        while (!anoEncontrado) {
            System.out.println("\nEscolha um ano pelo código (ou digite 0 para sair): ");
            anoBuscado = leitura.nextLine();

            if (anoBuscado.equalsIgnoreCase("0")) {
                System.out.println("Saindo...");
                return;
            }

            for (DadosAno ano : anos) {
                if (anoBuscado.equalsIgnoreCase(ano.codigo())) {
                    System.out.println("\nAno escolhido: " + ano.nome());
                    anoEncontrado = true;
                    break;
                }
            }

            if (!anoBuscado.isEmpty())
            {
                if (!anoEncontrado) {
                    System.out.println("Código inválido! Tente novamente.");
                }
            }
        }

        json = consumo.obterDados(ENDERECO + tipoVeiculo + "/marcas/" + marcaBuscada + "/modelos/" + modeloBuscado + "/anos/" + anoBuscado);
        Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
        System.out.println("Modelo: " + veiculo.modelo());
        System.out.println("Ano: " + veiculo.ano());
        System.out.println("Marca: " + veiculo.marca());
        System.out.println("Combustível: " + veiculo.combustivel());
        System.out.println("Valor médio: " + veiculo.valor());
        System.out.println("Mês de referência: " + veiculo.mesReferencia());

        leitura.close();
    }
}
