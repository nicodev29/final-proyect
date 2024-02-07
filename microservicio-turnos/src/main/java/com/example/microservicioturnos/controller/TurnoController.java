package com.example.microservicioturnos.controller;

import com.example.microservicioturnos.dto.TurnoDTO;
import com.example.microservicioturnos.model.Turno;
import com.example.microservicioturnos.service.ITurnoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The TurnoController class handles the request mappings for appointments.
 */
@RestController
@RequestMapping("/turnos")
public class TurnoController {

    /**
     * The TurnoService class is autowired to handle the business logic related to appointments.
     */
    private final ITurnoService turnoService;

    /**
     * The TurnoController class is responsible for handling the request mappings related to appointments.
     */
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    /**
     * Creates a new appointment.
     *
     * @param appointment the details of the appointment to be created
     * @return a string indicating the success of the appointment creation
     */
    @PostMapping
    public String createAppointment(@RequestBody TurnoDTO appointment) {
        turnoService.saveTurno(
                appointment.getFecha(),
                appointment.getTratamiento(),
                appointment.getDniPaciente());
        return "The appointment was successfully created";
    }

    /**
     * This method retrieves a list of appointments.
     *
     * @return The list of appointments.
     */
    @GetMapping
    List<Turno> getAppointment() {
        return turnoService.getTurnos();
    }

    /**
     * Deletes an appointment with the given ID.
     *
     * @param id the ID of the appointment to be deleted
     * @return a success message indicating that the appointment was successfully deleted
     */
    @DeleteMapping("/{id}")
    String deleteAppointment(@PathVariable Long id) {
        turnoService.deleteTurno(id);
        return "The appointment was successfully deleted";
    }

    /**
     * Edits an appointment with the given ID by replacing it with the new appointment details.
     *
     * @param id               the ID of the appointment to edit
     * @param appointmentToEdit the new appointment details to replace the existing appointment
     *
     * @return the edited appointment
     */
    @PutMapping("/{id}")
    Turno editAppointment(@PathVariable Long id, @RequestBody Turno appointmentToEdit) {
        turnoService.editTurno(id, appointmentToEdit);
        Turno editedAppointment = turnoService.findTurno(id);
        return editedAppointment;
    }

    /**
     * Retrieves the appointment with the specified ID.
     *
     * @param id the ID of the appointment to retrieve
     * @return the appointment with the specified ID
     */
    @GetMapping("/{id}")
    Turno getAppointment(@PathVariable Long id) {
        return turnoService.findTurno(id);
    }
}
