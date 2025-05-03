package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.mailer.EnvioEmail;
import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.servicios.ClinicaServicio;
import co.edu.uniquindio.clinica.suscripcion.Suscripcion;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CrearCitaControlador {

    @FXML private ComboBox<Paciente> SeleccionePaciente;
    @FXML private ComboBox<Servicio> SeleccioneServicio;
    @FXML private DatePicker SeleccioneFecha;
    @FXML private ComboBox<String> SeleccioneHora;
    @FXML private TextField IngreseNotas;

    private final ClinicaServicio clinica = ControladorPrincipal.getInstancia().getClinica();

    @FXML
    public void initialize() {
        SeleccionePaciente.getItems().addAll(clinica.getPacienteServicio().getPacienteRepositorio().getPacientes());
        SeleccioneServicio.getItems().addAll(clinica.getServicios());

        for (int hour = 8; hour <= 17; hour++) {
            SeleccioneHora.getItems().add(String.format("%02d:00", hour));
            SeleccioneHora.getItems().add(String.format("%02d:30", hour));
        }
    }

    @FXML
    public void crearCita() {
        try {
            if (SeleccionePaciente.getValue() == null || SeleccioneServicio.getValue() == null ||
                    SeleccioneFecha.getValue() == null || SeleccioneHora.getValue() == null) {
                mostrarAlerta("Debe completar todos los campos");
                return;
            }

            Paciente paciente = SeleccionePaciente.getValue();
            Servicio servicio = SeleccioneServicio.getValue();
            LocalDate fecha = SeleccioneFecha.getValue();
            String horaTexto = SeleccioneHora.getValue();
            String notas = IngreseNotas.getText();
            LocalTime hora = LocalTime.parse(horaTexto);
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

            clinica.agendarCita(paciente, servicio, fechaHora, notas);

            // Obtener suscripción y generar factura
            Suscripcion suscripcion = paciente.getSuscripcion();
            Factura factura = suscripcion.generarFacturaCobro(servicio, paciente.getNombre());

            // Crear mensaje de la factura
            String mensajeFactura = "Hola " + paciente.getNombre() + ",\n\n" +
                    "Tu cita ha sido agendada correctamente:\n\n" +
                    "📅 Fecha: " + fecha + "\n" +
                    "⏰ Hora: " + horaTexto + "\n" +
                    "🩺 Servicio: " + factura.getServicio() + "\n";

            if (notas != null && !notas.isBlank()) {
                mensajeFactura += "📝 Notas: " + notas + "\n";
            }

            mensajeFactura += "\n" +
                    "💳 Subtotal: $" + factura.getSubtotal() + "\n" +
                    "🏷️ Total a pagar (suscripción " + suscripcion.getTipo() + "): $" + factura.getTotal() + "\n\n" +
                    "¡Gracias por confiar en nuestra clínica!";

            // Enviar factura por email
            EnvioEmail.enviarNotificacion(paciente.getCorreo(), "Factura de tu cita", mensajeFactura);

            mostrarAlerta("Cita creada exitosamente. La factura fue enviada al correo.");
            limpiarCampos();

        } catch (Exception e) {
            mostrarAlerta("Error: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        SeleccionePaciente.setValue(null);
        SeleccioneServicio.setValue(null);
        SeleccioneFecha.setValue(null);
        SeleccioneHora.setValue(null);
        IngreseNotas.clear();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
