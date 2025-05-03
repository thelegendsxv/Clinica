package co.edu.uniquindio.clinica.servicios;

import co.edu.uniquindio.clinica.suscripcion.Suscripcion;
import co.edu.uniquindio.clinica.mailer.EnvioEmail;
import co.edu.uniquindio.clinica.modelo.entidades.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter

public class ClinicaServicio implements IClinicaServicio {

    private final CitaServicio citaServicio;
    private final PacienteServicio pacienteServicio;

    public ClinicaServicio(){
        citaServicio = new CitaServicio();
        pacienteServicio = new PacienteServicio();
    }

    private final List<Servicio> servicios = new LinkedList<>();


    public void registrarPaciente(String id, String nombre, String telefono, String correo, Suscripcion suscripcion) throws Exception {
        pacienteServicio.registrarPaciente(id, nombre, telefono, correo, suscripcion);

    }


    @Override
    public void agendarCita(Paciente paciente, Servicio servicio, LocalDateTime fecha, String nota) throws Exception {
        Factura factura = paciente.getSuscripcion().generarFacturaCobro(servicio, paciente.getNombre());
        Cita cita = citaServicio.agendarCita(paciente, servicio, fecha, factura, nota);
        enviarCorreoVerificacion(paciente, cita);
    }

    @Override
    public void cancelarCita(String idCita) throws Exception {
        citaServicio.cancelarCita(idCita);
        EnvioEmail.enviarNotificacion("Email destinatario", "Cita cancelada", "Tu cita ha sido cancelada exitosamente.");
    }

    @Override
    public void registrarServicio(String nombre, double precio) throws Exception {
        if (buscarServicioPorNombre(nombre) != null) {
            throw new Exception("El servicio ya existe.");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del servicio no puede estar vacío.");
        }

        Servicio servicio = Servicio.builder()
                .nombre(nombre)
                .precio(precio)
                .build();

        servicios.add(servicio);
    }



    private Servicio buscarServicioPorNombre(String nombre) {
        for (Servicio servicio : servicios) {
            if (servicio.getNombre().equalsIgnoreCase(nombre)) {
                return servicio;
            }
        }
        return null;
    }



    private void enviarCorreoVerificacion(Paciente paciente, Cita cita) {
        String codigo = UUID.randomUUID().toString();

        String asunto = "Confirmación de Cita Médica - Clínica Salud";
        String mensaje = "Hola " + paciente.getNombre() + ",\n\n" +
                "Tu cita ha sido agendada exitosamente:\n" +
                "- Servicio: " + cita.getServicio().getNombre() + "\n" +
                "- Fecha y hora: " + cita.getFecha() + "\n\n" +
                "Código: " + codigo + "\n\n" +
                "Gracias por confiar en nosotros.\nClínica Salud";

        EnvioEmail.enviarNotificacion(paciente.getCorreo(), asunto, mensaje);
    }
}