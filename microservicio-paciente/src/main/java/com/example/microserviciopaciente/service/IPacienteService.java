package com.example.microserviciopaciente.service;

import com.example.microserviciopaciente.model.Paciente;

import java.util.List;

public interface IPacienteService {
     List<Paciente> getPacientes();
     void savePaciente(Paciente pac);
     void deletePaciente(Long id);
     Paciente findPaciente(Long id);
     void editPaciente (Long id, Paciente pac);
     Paciente findPacienteDni(String dni);
}
