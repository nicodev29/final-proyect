package com.example.microservicioturnos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoDTO {

    private LocalDate fecha;
    private String tratamiento;
    private String dniPaciente;

}
