package com.example.microserviciopaciente.service;

import com.example.microserviciopaciente.model.Paciente;
import com.example.microserviciopaciente.repository.IPacienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteService implements IPacienteService{

    private final IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    @Override
    public List<Paciente> getPacientes() {
        return pacienteRepository.findAll();
    }
    @Override
    public void savePaciente(Paciente pac) {
        pacienteRepository.save(pac);
    }
    @Override
    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente findPaciente(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    @Override
    public void editPaciente(Long id, Paciente pac) {
        this.savePaciente(pac);
    }

    @Override
    public Paciente findPacienteDni(String dni) {
        return pacienteRepository.findPacienteDni(dni);
    }


}
