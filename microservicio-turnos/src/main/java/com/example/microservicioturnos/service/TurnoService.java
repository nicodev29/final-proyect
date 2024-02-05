package com.example.microservicioturnos.service;

import com.example.microservicioturnos.model.Paciente;
import com.example.microservicioturnos.model.Turno;
import com.example.microservicioturnos.repository.ITurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class TurnoService implements ITurnoService{

    @Autowired
    private final ITurnoRepository turnoRepository;

    @Autowired
    private final RestTemplate clienteRest;

    public TurnoService(ITurnoRepository turnoRepository, RestTemplate clienteRest) {
        this.turnoRepository = turnoRepository;
        this.clienteRest = clienteRest;
    }

    @Override
    public List<Turno> getTurnos() {
        return turnoRepository.findAll();
    }
    @Override
    public void saveTurno(LocalDate fecha, String tratamiento, String dniPaciente) {

        Paciente paciente = clienteRest.getForObject("http://localhost:8080/api/pacientes/traerdni/"+dniPaciente, Paciente.class);
        String nombrePaciente = paciente.getNombre() + " " + paciente.getApellido();

        Turno turno = new Turno();
        turno.setFecha(fecha);
        turno.setTratamiento(tratamiento);
        turno.setNombrePaciente(nombrePaciente);
        turnoRepository.save(turno);

        System.out.println("El turno fue creado correctamente\n" +
                "ID: " + turno.getId_turno() + "\n" +
                "Paciente: " + turno.getNombrePaciente() + "\n" +
                "Fecha: " + turno.getFecha());

    }
    @Override
    public void deleteTurno(Long id) {
        turnoRepository.deleteById(id);
    }
    @Override
    public Turno findTurno(Long id) {
        return turnoRepository.findById(id).orElse(null);
    }
    @Override
    public void editTurno(Long id, Turno turno){

        Turno turno1 = this.findTurno(id);
        turno1.setFecha(turno.getFecha());
        turno1.setTratamiento(turno.getTratamiento());
        turno1.setNombrePaciente(turno.getNombrePaciente());

        turnoRepository.save(turno1);

    }

}
