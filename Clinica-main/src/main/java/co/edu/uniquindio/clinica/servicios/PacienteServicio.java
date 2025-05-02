package co.edu.uniquindio.clinica.servicios;

import co.edu.uniquindio.clinica.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.repositorios.PacienteRepositorio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteServicio {
    private PacienteRepositorio pacienteRepositorio;

    public PacienteServicio() {
        this.pacienteRepositorio = new PacienteRepositorio();
    }
    /*
    /Metodos
     */
    public void registrarPaciente(String id, String nombre, String telefono, String correo, Suscripcion suscripcion) throws Exception {
        validarDatosPaciente(id, nombre, telefono, correo, suscripcion);
        Paciente paciente = (crearPaciente(id, nombre, telefono, correo, suscripcion));
        pacienteRepositorio.agregarPaciente(paciente);

    }

    public Paciente crearPaciente(String id, String nombre, String telefono, String correo, Suscripcion suscripcion) throws Exception {
        esNulo(id);
        esDuplicado(id);

        Paciente paciente = Paciente.builder()
                .id(id)
                .nombre(nombre)
                .telefono(telefono)
                .correo(correo)
                .suscripcion(suscripcion)
                .build();
        return paciente;
    }

    public void esDuplicado(String id) throws Exception {
        if(pacienteRepositorio.existePaciente(id)){
            throw new Exception("El paciente ya existe.");
        }
    }
    public void esNulo(String id) throws Exception {
        if (pacienteRepositorio.buscarPacientePorId(id) != null) {
            throw new Exception("El paciente ya existe.");
        }
    }

    private void validarDatosPaciente(String id, String nombre, String telefono, String correo, Suscripcion suscripcion) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (telefono == null || !telefono.matches("\\d{7,10}")) {
            throw new IllegalArgumentException("El teléfono debe tener entre 7 y 10 dígitos.");
        }
        if (correo == null || !correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("El correo no tiene un formato válido.");
        }
        if (suscripcion == null) {
            throw new IllegalArgumentException("La suscripción no puede ser nula.");
        }
    }

}
