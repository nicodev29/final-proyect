package com.example.microserviciopaciente.service;

import com.example.microserviciopaciente.model.Paciente;
import com.example.microserviciopaciente.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteService implements IPacienteService{

    /**
     * Repository class for managing Paciente entities.
     *
     * This class is responsible for handling CRUD operations (Create, Read, Update, Delete)
     * related to the Paciente entity in the database.
     *
     * The implementation of this repository is based on the JpaRepository interface.
     * It provides a set of default methods to perform common database operations.
     * Additional custom methods are defined in this interface as well.
     *
     * This repository is autowired in the PacienteService class to access and manipulate
     * the Paciente entities in the database.
     *
     * Note: The Paciente entity is a JPA entity annotated with @Entity and mapped to a table
     * in the database. It represents a patient with attributes such as dni, nombre, apellido,
     * fechaNacimiento, and telefono.
     *
     * @see IPacienteService
     * @see Paciente
     */
    @Autowired
    private final IPacienteRepository pacienteRepository;
    /**
     * Initializes a new instance of the PacienteService class.
     *
     * @param pacienteRepository The implementation of IPacienteRepository used for data access.
     */
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    /**
     * Retrieves a list of all patients.
     *
     * @return A list of Patient objects representing all the patients.
     */
    @Override
    public List<Paciente> getPacientes() {
        return pacienteRepository.findAll();
    }
    /**
     * Saves a Paciente object to the repository.
     *
     * @param pac The Paciente object to be saved.
     */
    @Override
    public void savePaciente(Paciente pac) {
        pacienteRepository.save(pac);
    }
    /**
     * Deletes a paciente from the database based on the given id.
     *
     * @param id the id of the paciente to delete
     */
    @Override
    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    /**
     * Finds a Paciente by their ID.
     *
     * @param id the ID of the Paciente
     * @return the Paciente with the specified ID, or {@code null} if no Paciente is found
     */
    @Override
    public Paciente findPaciente(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    /**
     * Updates a Paciente with the given id by saving the new Paciente object.
     *
     * @param id The id of the Paciente to be updated.
     * @param pac The new Paciente object to be saved.
     */
    @Override
    public void editPaciente(Long id, Paciente pac) {
        this.savePaciente(pac);
    }

    /**
     * Returns the Paciente object associated with the given DNI.
     *
     * @param dni the DNI of the paciente
     * @return the Paciente object if found, otherwise returns null
     */
    @Override
    public Paciente findPacienteDni(String dni) {
        return pacienteRepository.findPacienteDni(dni);
    }

}