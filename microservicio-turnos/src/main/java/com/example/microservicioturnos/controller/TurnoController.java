package com.example.microservicioturnos.controller;
import com.example.microservicioturnos.dto.TurnoDTO;
import com.example.microservicioturnos.model.Turno;
import com.example.microservicioturnos.service.ITurnoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * La clase TurnoController se encarga de manejar las asignaciones de solicitudes para las citas.
 */
@RestController
@RequestMapping("/turnos")
public class TurnoController {
    /**
     * La clase TurnoService se inyecta automáticamente para manejar la lógica empresarial relacionada con las citas.
     */
    private final ITurnoService turnoService;

    @Value("${server.port}")
    private int serverPort;

    /**
     * La clase TurnoController es responsable de manejar las asignaciones de solicitudes relacionadas con las citas.
     */
    private TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    /**
     * Crea un nuevo turnoDTO.
     *
     * @param turnoDTO los detalles del turnoDTO a crear
     * @return una cadena indicando el éxito de la creación del turnoDTO
     */
    @PostMapping("/crear")
    public String createAppointment(@RequestBody TurnoDTO turnoDTO) {
        turnoService.saveTurno(
                turnoDTO.getFecha(),
                turnoDTO.getTratamiento(),
                turnoDTO.getDniPaciente());
        return "El turnoDTO ha sido creado con éxito";
    }

    /**
     * Este método recupera una lista de citas.
     *
     * @return La lista de citas.
     */
    @GetMapping
    List<Turno> obtenerTurno() {
        return turnoService.getTurnos();
    }

    /**
     * Elimina una cita con el ID dado.
     *
     * @param id el ID de la cita a eliminar
     * @return un mensaje de éxito indicando que la cita se eliminó correctamente
     */
    @DeleteMapping("/{id}")
    String deleteAppointment(@PathVariable Long id) {
        turnoService.deleteTurno(id);
        return "La cita ha sido eliminada con éxito";
    }

    /**
     * Edita una cita con el ID dado reemplazándola con los nuevos detalles de la cita.
     *
     * @param id el ID de la cita para editar
     * @param turno los nuevos detalles de la cita para reemplazar la cita existente
     *
     * @return la cita editada
     */
    @PutMapping("/{id}")
    Turno editAppointment(@PathVariable Long id, @RequestBody Turno turno) {
        turnoService.editTurno(id, turno);
        Turno turnoEdit = turnoService.findTurno(id);
        return turnoEdit;
    }

    /**
     * Recupera la cita con el ID especificado.
     *
     * @param id el ID de la cita a recuperar
     * @return la cita con el ID especificado
     */
    @GetMapping("/{id}")
    Turno obtenerTurno(@PathVariable Long id) {
        System.out.println("El puerto del servidor es: " + serverPort);
        return turnoService.findTurno(id);
    }
}