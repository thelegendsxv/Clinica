package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.modelo.enumer.EstadoCita;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;

public class ListarCitaControlador {

    @FXML private TableColumn<Cita, String> TablaId;
    @FXML private TableColumn<Cita, String> TablaPaciente;
    @FXML private TableColumn<Cita, String> TablaServicio;
    @FXML private TableColumn<Cita, String> TablaFecha;
    @FXML private TableColumn<Cita, String> TablaEstado;
    @FXML private TableColumn<Cita, String> TablaNotas;
    @FXML private TableView<Cita> tablaCitas;
    @FXML private Button btnCancelar;

    @FXML
    void initialize() {
        configurarColumnas();
        cargarDatos();

        // Deshabilitar el botón al inicio
        btnCancelar.setDisable(true);

        // Habilitar botón solo si hay una cita seleccionada
        tablaCitas.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            btnCancelar.setDisable(newSel == null);
        });

        btnCancelar.setOnAction(e -> cancelarCitaSeleccionada());
    }

    private void configurarColumnas() {
        TablaId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        TablaPaciente.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPaciente().getNombre()));
        TablaServicio.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getServicio().getNombre()));
        TablaFecha.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        TablaEstado.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado().toString()));
        TablaNotas.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getNotas() != null ? c.getValue().getNotas() : ""));
    }

    private void cargarDatos() {
        tablaCitas.setItems(FXCollections.observableArrayList(
                ControladorPrincipal.getInstancia().getClinica()
                        .getCitaServicio().getCitaRepositorio().getCitas()
        ));
    }

    private void cancelarCitaSeleccionada() {
        Cita cita = tablaCitas.getSelectionModel().getSelectedItem();
        EstadoCita EstadoCita = null;
        if (cita != null && cita.getEstado() != EstadoCita.CANCELADA) {
            cita.setEstado(EstadoCita.CANCELADA);
            tablaCitas.refresh();
        }
    }
}
