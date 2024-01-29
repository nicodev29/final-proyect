package com.example.microservicioturnos.controller;

import com.example.microservicioturnos.model.Turno;
import com.example.microservicioturnos.service.ITurnoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private ITurnoService turnoServ;

    @PostMapping("/crear")
    public String crearTurno (@RequestBody LocalDate fecha,
                              @RequestBody String tratamiento,
                              @RequestBody String dniPaciente) {

        turnoServ.saveTurno(fecha, tratamiento, dniPaciente);

        return "El turno fue creado correctamente";
    }

    @GetMapping("/todos")
    List <Turno> traerTurnos () {
        return turnoServ.getTurnos();
    }

    @DeleteMapping("/borrar/{id}")
    String deleteTurno (@PathVariable Long id) {
        turnoServ.deleteTurno(id);

        return "El turno fue eliminado correctamente";
    }

    @PutMapping ("/editar/{id_original}")
    Turno editTurno (@PathVariable Long id_original,
                     @RequestBody Turno turnoEditar) {

        turnoServ.editTurno(id_original, turnoEditar);
        Turno turnoEditado = turnoServ.findTurno(id_original);

        return turnoEditado;

    }

    @GetMapping ("/traer/{id}")
    Turno traerTurno (@PathVariable Long id) {
        return turnoServ.findTurno(id);
    }

}
