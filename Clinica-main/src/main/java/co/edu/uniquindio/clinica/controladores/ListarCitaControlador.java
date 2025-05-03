package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListarCitaControlador {

    @FXML
    private TableColumn<Cita, String> TablaId;

    @FXML
    private TableColumn<Cita, String> TablaPaciente;

    @FXML
    private TableColumn<Cita, String> TablaServicio;

    @FXML
    private TableColumn<Cita, String> TablaFecha;

    @FXML
    private TableColumn<Cita, String> TablaEstado;

    @FXML
    private TableColumn<Cita, String> TablaNotas;

    @FXML
    private Label TextListaDeCitas;

    @FXML
    private TableView<Cita> tablaCitas;

    private final ObservableList<Cita> citas = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        configurarColumnas();
        actualizarTabla();
    }

    private void configurarColumnas() {
        TablaId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPaciente().getId()));
        TablaPaciente.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPaciente().getNombre()));
        TablaServicio.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getServicio().getNombre()));
        TablaFecha.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFecha().toString()));
        TablaEstado.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado().toString()));
        TablaNotas.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNotas()));
    }

    private void actualizarTabla() {
        citas.setAll(ControladorPrincipal.getInstancia().getClinica().getCitaServicio().getCitaRepositorio().getCitas());
        tablaCitas.setItems(citas);
        tablaCitas.refresh();
    }
}
