package com.example.microserviciopaciente.controller;

import com.example.microserviciopaciente.model.Paciente;
import com.example.microserviciopaciente.service.IPacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PacienteController {

    private final IPacienteService pacienteServ;

    public PacienteController(IPacienteService pacienteServ) {
        this.pacienteServ = pacienteServ;
    }

    //1- crear un nuevo paciente
    @PostMapping("/pacientes/crear")
    public String crearPaciente (@RequestBody Paciente pac) {
        pacienteServ.savePaciente(pac);

        return "El paciente fue creado correctamente";
    }

    //2- traer todos los pacientes
    @GetMapping("/pacientes/todos")
    public List<Paciente> traerPacientes () {
        return pacienteServ.getPacientes();
    }
    //3- Eliminar un paciente
    @DeleteMapping("/pacientes/borrar/{id}")
    public String deletePaciente(@PathVariable Long id) {
        pacienteServ.deletePaciente(id);

        return "El paciente fue eliminado correctamente";
    }

    //4- Editar un paciente
    @PutMapping ("/pacientes/editar/{id_original}")
    public Paciente editPaciente (@PathVariable Long id_original,
                                  @RequestBody Paciente pacienteEditar) {

        pacienteServ.editPaciente(id_original, pacienteEditar);
        Paciente pacienteEditado = pacienteServ.findPaciente(id_original);

        return pacienteEditado;

    }

    //5- obtener un paciente en particular
    @GetMapping ("/pacientes/traer/{id}")
    public Paciente traerPaciente (@PathVariable Long id) {
        return pacienteServ.findPaciente(id);
    }

}
