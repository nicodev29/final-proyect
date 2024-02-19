package com.example.microservicioturnos.service;

import com.example.microservicioturnos.model.Turno;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoService {

    List<Turno> getTurnos();
    void saveTurno(LocalDate fecha, String tratamiento, String dniPaciente);

    void deleteTurno(Long id);

    Turno findTurno(Long id);

    void editTurno(Long id, Turno turno);

}
