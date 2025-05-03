package co.edu.uniquindio.clinica.servicios;

import co.edu.uniquindio.clinica.modelo.enumer.TipoSuscripcion;
import co.edu.uniquindio.clinica.suscripcion.Suscripcion;
import co.edu.uniquindio.clinica.mailer.EnvioEmail;
import co.edu.uniquindio.clinica.modelo.entidades.*;
import co.edu.uniquindio.clinica.suscripcionfactory.SinSuscripcionFactory;
import co.edu.uniquindio.clinica.suscripcionfactory.SuscripcionBasicaFactory;
import co.edu.uniquindio.clinica.suscripcionfactory.SuscripcionFactory;
import co.edu.uniquindio.clinica.suscripcionfactory.SuscripcionPremiumFactory;
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

    private final List<Servicio> servicios = List.of(
            new Servicio("Consulta General", 80000, "S001"),
            new Servicio("Odontología", 120000, "S002"),
            new Servicio("Terapia Física", 100000, "S003"),
            new Servicio("Laboratorio Clínico", 70000, "S004"),
            new Servicio("Radiografía", 95000, "S005"),
            new Servicio("Ecografía", 110000, "S006"),
            new Servicio("Consulta Pediátrica", 85000, "S007"),
            new Servicio("Psicología", 90000, "S008"),
            new Servicio("Nutrición", 75000, "S009"),
            new Servicio("Cardiología", 130000, "S010"),
            new Servicio("Dermatología", 115000, "S011"),
            new Servicio("Ginecología", 125000, "S012"),
            new Servicio("Oftalmología", 95000, "S013"),
            new Servicio("Vacunación", 60000, "S014"),
            new Servicio("Chequeo Médico General", 90000, "S015"),
            new Servicio("Medicina Interna", 140000, "S016"),
            new Servicio("Neumología", 135000, "S017"),
            new Servicio("Neurología", 145000, "S018"),
            new Servicio("Urología", 130000, "S019"),
            new Servicio("Endocrinología", 128000, "S020")
    );


    public void registrarPaciente(String id, String nombre, String telefono, String correo, TipoSuscripcion tipo) throws Exception {
        crearSuscripcionFactory(tipo);
        Suscripcion suscripcion = crearSuscripcionFactory(tipo).crearSuscripcion();
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
    public void registrarServicio(String nombre, double precio, String id) throws Exception {
        if (buscarServicioPorNombre(nombre) != null) {
            throw new Exception("El servicio ya existe.");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del servicio no puede estar vacío.");
        }

        Servicio servicio = new Servicio(nombre, precio, id);

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
    public SuscripcionFactory crearSuscripcionFactory(TipoSuscripcion tipo) {

        return switch (tipo) {
            case TipoSuscripcion.BASICA -> new SuscripcionBasicaFactory();
            case TipoSuscripcion.PREMIUM -> new SuscripcionPremiumFactory();
            case TipoSuscripcion.SINSUSCRIPCION -> new SinSuscripcionFactory();
            default -> throw new IllegalArgumentException("Tipo de suscripción no válido");
        };
    }
}