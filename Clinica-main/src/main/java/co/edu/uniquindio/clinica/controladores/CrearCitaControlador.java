package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.modelo.enumer.EstadoCita;
import co.edu.uniquindio.clinica.servicios.ClinicaServicio;
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
            // Validar campos
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
            mostrarAlerta("Cita creada exitosamente");
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