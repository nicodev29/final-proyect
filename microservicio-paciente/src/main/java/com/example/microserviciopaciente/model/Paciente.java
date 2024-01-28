package com.example.microserviciopaciente.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;
    private String dni;
    private String nombre;
    private String apellido;
    @Temporal(TemporalType.DATE)
    private LocalDate fechaNacimiento;
    private String telefono;

}
