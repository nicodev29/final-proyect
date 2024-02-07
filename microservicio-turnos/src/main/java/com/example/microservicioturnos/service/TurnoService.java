package com.example.microservicioturnos.service;
import com.example.microservicioturnos.model.Paciente;
import com.example.microservicioturnos.model.Turno;
import com.example.microservicioturnos.repository.ITurnoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.List;

/**
 * This class provides methods to manage turnos (appointments).
 */
@Service
public class TurnoService implements ITurnoService{

    private final ITurnoRepository turnoRepository;
    /**
     * The `clienteRest` variable is an instance of the `RestTemplate` class.
     * It is used to make HTTP requests to a RESTful web service.
     * The `clienteRest` variable is declared as private and final, meaning it cannot be reassigned or modified.
     *
     * This variable is used in the `TurnoService` class to retrieve a `Paciente` object from the RESTful web service
     * and perform operations related to appointments (`Turno` objects).
     *
     * Example usage:
     *
     * ```
     * Paciente paciente = clienteRest.getForObject("http://localhost:8080/api/pacientes/dni/"+dniPaciente, Paciente.class);
     * ```
     */
    private final RestTemplate clienteRest;
    /**
     * This class provides methods to manage turnos (appointments).
     */
    public TurnoService(ITurnoRepository turnoRepository, RestTemplate clienteRest) {
        this.turnoRepository = turnoRepository;
        this.clienteRest = clienteRest;
    }
    /**
     * Retrieves all the turnos (appointments).
     *
     * @return A list of Turno objects representing the turnos.
     */
    @Override
    public List<Turno> getTurnos() {
        return turnoRepository.findAll();
    }
    /**
     * Saves a turno (appointment) with the given fecha, tratamiento, and dniPaciente.
     * Retrieves the paciente from the API using the dniPaciente.
     * Sets the nombrePaciente as the concatenation of paciente's nombre and apellido.
     * Creates a new Turno object and sets its fecha, tratamiento, and nombrePaciente.
     * Saves the turno in the turnoRepository.
     * Prints the ID, nombrePaciente, and fecha of the turno.
     *
     * @param fecha        the fecha of the turno
     * @param tratamiento  the tratamiento of the turno
     * @param dniPaciente  the DNI of the paciente
     */
    @Override
    public void saveTurno(LocalDate fecha, String tratamiento, String dniPaciente) {
        Paciente paciente = clienteRest.getForObject("http://localhost:8080/api/pacientes/dni/"+dniPaciente, Paciente.class);
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
    /**
     * Deletes a turno by its ID.
     *
     * @param id The ID of the turno to be deleted.
     */
    @Override
    public void deleteTurno(Long id) {
        turnoRepository.deleteById(id);
    }
    /**
     * Finds a turno (appointment) by its ID.
     *
     * @param id The ID of the turno to find.
     * @return The found turno, or null if no turno with the given ID exists.
     */
    @Override
    public Turno findTurno(Long id) {
        return turnoRepository.findById(id).orElse(null);
    }
    /**
     * Edits a turno with the given ID.
     *
     * @param id    the ID of the turno to edit
     * @param turno the updated turno object
     */
    @Override
    public void editTurno(Long id, Turno turno){
        Turno turno1 = this.findTurno(id);
        turno1.setFecha(turno.getFecha());
        turno1.setTratamiento(turno.getTratamiento());
        turno1.setNombrePaciente(turno.getNombrePaciente());
        turnoRepository.save(turno1);
    }
}