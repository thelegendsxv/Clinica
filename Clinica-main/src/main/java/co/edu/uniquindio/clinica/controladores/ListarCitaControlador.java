package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.format.DateTimeFormatter;

public class ListarCitaControlador {

    @FXML private TableColumn<Cita, String> TablaId;
    @FXML private TableColumn<Cita, String> TablaPaciente;
    @FXML private TableColumn<Cita, String> TablaServicio;
    @FXML private TableColumn<Cita, String> TablaFecha;
    @FXML private TableColumn<Cita, String> TablaEstado;
    @FXML private TableColumn<Cita, String> TablaNotas;
    @FXML private TableView<Cita> tablaCitas;

    @FXML
    void initialize() {
        configurarColumnas();
        cargarDatos();
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
}