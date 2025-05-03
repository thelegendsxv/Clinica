package co.edu.uniquindio.clinica.servicios;

import co.edu.uniquindio.clinica.modelo.enumer.TipoSuscripcion;
import co.edu.uniquindio.clinica.suscripcion.Suscripcion;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;

import java.time.LocalDateTime;

public interface IClinicaServicio {
    void registrarPaciente(String id, String nombre, String telefono, String correo, TipoSuscripcion tipoSuscripcion) throws Exception;

    void registrarServicio(String nombre, double precio, String id) throws Exception;

    void agendarCita(Paciente paciente, Servicio servicio, LocalDateTime fecha, String nota) throws Exception;

    void cancelarCita(String idCita) throws Exception;

}

