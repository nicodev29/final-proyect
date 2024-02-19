package com.example.microserviciopaciente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicioPacienteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioPacienteApplication.class, args);
    }

}
