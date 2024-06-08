package com.example.projetospringapi;

import com.example.projetospringapi.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoSpringApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoSpringApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.exibeMenu();

    }
}
