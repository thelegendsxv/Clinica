package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.enumer.EstadoCita;
import co.edu.uniquindio.clinica.modelo.enumer.TipoDescuento;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Clinica {
    private List<Paciente> pacientes;
    private List<Cita> citas;
    private List<Servicio> servicios;

    public Clinica (){
        this.pacientes = new LinkedList<>();
        this.citas = new LinkedList<>();
        this.servicios = new LinkedList<>();
    }

    public void agregarPaciente(String id, String nombre, String telefono, String correo, Suscripcion suscripcion) throws Exception {
        validarDatos(id, nombre, telefono, correo, suscripcion);
        validarUsuario(id);

        Paciente paciente =  Paciente.builder()
                .id(id)
                .nombre(nombre)
                .telefono(telefono)
                .correo(correo)
                .suscripcion(suscripcion)
                .build();

        pacientes.add(paciente);
        System.out.println("Paciente agregado");

    }

    public void validarDatos(String id, String nombre, String telefono, String correo, Suscripcion suscripcion)throws Exception {

            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID no puede estar vacío.");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }
            if (telefono == null || !telefono.matches("\\d{7,10}")) { // entre 7 y 10 dígitos
                throw new IllegalArgumentException("El teléfono debe tener solo números y entre 7 y 10 dígitos.");
            }
            if (correo == null || !correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new IllegalArgumentException("El correo no tiene un formato válido.");
            }
            if (suscripcion == null) {
                throw new IllegalArgumentException("La suscripción debe ser 'Basica' o 'Premium'.");
            }
        }

        public void validarUsuario(String id) throws Exception {
         for (Paciente paciente : pacientes){
             if (paciente.getId().equals(id)){
                 throw new Exception("El usuario ya existe.");
             }
         }
        }

    public void agregarServicio(String nombre, double precio) throws Exception {
        validarServicio(nombre);

        Servicio servicio = Servicio.builder()
                .nombre(nombre)
                .precio(precio)
                .build();

        servicios.add(servicio);
    }

    private void validarServicio(String nombre) throws Exception {
        for (Servicio servicio : servicios) {
            if (servicio.getNombre().equalsIgnoreCase(nombre)) {
                throw new Exception("El servicio ya existe.");
            }
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El servicio no puede ser nul0");
        }
    }

    public void agendarCita(Paciente paciente, Servicio servicio, LocalDateTime fecha) throws Exception {
        validarDatosCita(paciente, servicio, fecha);

        Factura factura = generarFactura(paciente, servicio);
        Cita cita = Cita.builder()
                .paciente(paciente)
                .servicio(servicio)
                .fecha(fecha)
                .estado(EstadoCita.AGENDADA)
                .factura(factura)
                .build();

        citas.add(cita);
        enviarCorreoVerificacion(paciente,cita);
    }

    public void validarDatosCita(Paciente paciente, Servicio servicio, LocalDateTime fecha) throws Exception {

        if (paciente == null) {
            throw new IllegalArgumentException("Paciente no puede ser nulo.");
        }
        if (servicio == null) {
            throw new IllegalArgumentException("Servicio no puede ser nulo.");
        }
        if (fecha == null || fecha.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha de la cita debe ser en el futuro.");
        }
    }
    public Factura generarFactura(Paciente paciente, Servicio servicio) throws Exception {
        Factura factura = paciente.getSuscripcion().generarFacturaCobro(servicio, paciente.getNombre());
        return factura;

    }

    public void cancelarCita(String idCita) throws Exception {
        Cita cita = buscarCitaPorId(idCita);
        if (cita != null) {
            cita.setEstado(EstadoCita.CANCELADA);
            System.out.println("Cita cancelada exitosamente.");
        } else {
            throw new Exception("Cita no encontrada.");
        }
    }

    public Cita buscarCitaPorId(String id) {
        for (Cita cita : citas) {
            if (cita.getId().equals(id)) {
                return cita;
            }
        }
        return null;
    }

    public void enviarCorreoVerificacion(Paciente paciente, Cita cita) throws Exception {
        // Generar un código único de verificación
        String codigoVerificacion = UUID.randomUUID().toString();

        String asunto = "Confirmación de Cita Médica - Clínica Salud";
        String mensaje = "Hola " + paciente.getNombre() + ",\n\n" +
                "Tu cita ha sido agendada exitosamente.\n\n" +
                "Detalles de la cita:\n" +
                "- Servicio: " + cita.getServicio().getNombre() + "\n" +
                "- Fecha y Hora: " + cita.getFecha().toString() + "\n\n" +
                "Por favor, llega 10 minutos antes de tu cita.\n\n" +
                "¡Gracias por confiar en nosotros!\n\n" +
                "Clínica Salud.";


        // Enviar el correo
        EnvioEmail.enviarNotificacion(paciente.getCorreo(), asunto, mensaje);

        System.out.println("Correo de verificación enviado a " + paciente.getCorreo());
    }


}

