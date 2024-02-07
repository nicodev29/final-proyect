package com.example.microserviciopaciente.controller;

import com.example.microserviciopaciente.model.Paciente;
import com.example.microserviciopaciente.service.IPacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
    private final IPacienteService pacienteServ;
    public PacienteController(IPacienteService pacienteServ) {
        this.pacienteServ = pacienteServ;
    }

    @PostMapping
    public String createPaciente(@RequestBody Paciente pac) {
        pacienteServ.savePaciente(pac);
        return "El paciente fue creado correctamente";
    }

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteServ.getPacientes();
    }

    @DeleteMapping("/{id}")
    public String deletePaciente(@PathVariable Long id) {
        pacienteServ.deletePaciente(id);
        return "El paciente fue eliminado correctamente";
    }

    @PutMapping("/{id}")
    public String updatePaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        pacienteServ.editPaciente(id, paciente);
        return "El paciente fue actualizado correctamente";
    }

    @GetMapping("/{id}")
    public Paciente getPaciente(@PathVariable Long id) {
        return pacienteServ.findPaciente(id);
    }

    @GetMapping("/dni/{dni}")
    public Paciente getPacienteByDni(@PathVariable String dni) {
        return pacienteServ.findPacienteDni(dni);
    }
}
