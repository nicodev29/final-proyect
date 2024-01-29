package com.example.microservicioturnos.service;

import com.example.microservicioturnos.model.Turno;
import com.example.microservicioturnos.repository.ITurnoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TurnoService implements ITurnoService{

    private ITurnoRepository turnoRepository;

    @Override
    public List<Turno> getTurnos() {
        return turnoRepository.findAll();
    }
    @Override
    public void saveTurno(LocalDate fecha, String tratamiento, String dniPaciente) {

        Turno turno = new Turno();
        turno.setFecha(fecha);
        turno.setTratamiento(tratamiento);
        turnoRepository.save(turno);

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
