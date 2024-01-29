package com.example.microservicioturnos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Paciente {

    private Long idPaciente;
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String telefono;
}
