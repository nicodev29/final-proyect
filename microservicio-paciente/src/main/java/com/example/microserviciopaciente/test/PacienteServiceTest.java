package com.example.microserviciopaciente.test;

import com.example.microserviciopaciente.model.Paciente;
import com.example.microserviciopaciente.repository.IPacienteRepository;
import com.example.microserviciopaciente.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @Mock
    IPacienteRepository pacienteRepository;

    @InjectMocks
    PacienteService pacienteService;


    @Test
    void shouldSavePaciente() {
        Paciente pac = new Paciente(); // Asume un constructor apropiado
        // No es necesario devolver nada para save, sólo verificamos la acción
        pacienteService.savePaciente(pac);
        verify(pacienteRepository).save(pac);
    }

    @Test
    void shouldDeletePaciente() {
        Long id = 1L; // Un ID de ejemplo
        pacienteService.deletePaciente(id);
        verify(pacienteRepository).deleteById(id);
    }

    @Test
    void shouldFindPacienteById() {
        Long id = 1L; // Un ID de ejemplo
        Paciente expectedPaciente = new Paciente(); // Asume un constructor apropiado
        given(pacienteRepository.findById(id)).willReturn(Optional.of(expectedPaciente));
        Paciente found = pacienteService.findPaciente(id);
        assertEquals(expectedPaciente, found);
    }

    @Test
    void shouldEditPaciente() {
        Long id = 1L; // Un ID de ejemplo
        Paciente pac = new Paciente(); // Asume un constructor apropiado
        pacienteService.editPaciente(id, pac);
        verify(pacienteRepository).save(pac);
    }
}