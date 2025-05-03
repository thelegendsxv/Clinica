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

        // Horas disponibles de 8:00 a 17:00 cada 30 minutos
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

            // Verificar disponibilidad
            for (Cita citaExistente : clinica.getCitaServicio().getCitaRepositorio().getCitas()) {
                if (citaExistente.getPaciente().equals(paciente) && citaExistente.getFecha().equals(fechaHora)) {
                    mostrarAlerta("El paciente ya tiene cita a esta hora");
                    return;
                }
                if (citaExistente.getServicio().equals(servicio) && citaExistente.getFecha().equals(fechaHora)) {
                    mostrarAlerta("El servicio ya está reservado a esta hora");
                    return;
                }
            }

            // Crear y guardar cita
            Cita nuevaCita = Cita.builder()
                    .paciente(paciente)
                    .servicio(servicio)
                    .fecha(fechaHora)
                    .estado(EstadoCita.AGENDADA)
                    .notas(notas)
                    .build();

            clinica.getCitaServicio().getCitaRepositorio().agregarCita(nuevaCita);
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