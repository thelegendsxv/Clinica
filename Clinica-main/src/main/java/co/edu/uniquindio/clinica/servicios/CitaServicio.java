package co.edu.uniquindio.clinica.servicios;

import co.edu.uniquindio.clinica.factory.Suscripcion;
import co.edu.uniquindio.clinica.mailer.EnvioEmail;
import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.modelo.enumer.EstadoCita;
import co.edu.uniquindio.clinica.repositorios.CitaRepositorio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
public class CitaServicio {
    private CitaRepositorio citaRepositorio;

    public CitaServicio() {
        citaRepositorio = new CitaRepositorio();
    }

    public Cita agendarCita(Paciente paciente, Servicio servicio, LocalDateTime fecha, Factura factura) throws Exception {
        validarDatosCita(paciente, servicio, fecha, factura);
        Cita cita = crearCita(paciente, servicio, fecha, factura);

        citaRepositorio.agregarCita(cita);

        return cita;
    }

    public Cita crearCita(Paciente paciente, Servicio servicio, LocalDateTime fecha, Factura factura) throws Exception {
        String id = generarNumeroUnicoCita();
        Cita cita = Cita.builder()
                .id(id)
                .paciente(paciente)
                .servicio(servicio)
                .fecha(fecha)
                .estado(EstadoCita.AGENDADA)
                .factura(factura)
                .build();
        return  cita;
    }
    public void validarDatosCita(Paciente paciente, Servicio servicio, LocalDateTime fecha, Factura factura) throws Exception {
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente no puede ser nulo.");
        }
        if (servicio == null) {
            throw new IllegalArgumentException("Servicio no puede ser nulo.");
        }
        if (fecha == null || fecha.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha debe ser futura.");
        }
        if (factura == null) {
            throw new IllegalArgumentException("La factura no puede ser nulo.");
        }
    }

    public void cancelarCita(String idCita) throws Exception {
        Cita cita = citaRepositorio.buscarCitaPorId(idCita);

        if (cita == null) {
            throw new Exception("Cita no encontrada.");
        }
        cita.setEstado(EstadoCita.CANCELADA);
    }

    private String generarNumeroUnicoCita() {
        String numero = generarNumeroAleatorio();
        while (citaRepositorio.buscarCitaPorId(numero) != null) {
            numero = generarNumeroAleatorio();
        }
        return numero;
    }

    private String generarNumeroAleatorio() {
        Random random = new Random();
        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            numero.append(random.nextInt(7));
        }
        return numero.toString();
    }
}
