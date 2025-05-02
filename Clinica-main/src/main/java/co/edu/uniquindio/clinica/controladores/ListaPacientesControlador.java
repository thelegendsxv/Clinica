package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListaPacientesControlador {

    @FXML
    private TableColumn<Paciente, String> correoCol;

    @FXML
    private TableColumn<Paciente, String> idCol;

    @FXML
    private TableColumn<Paciente, String> nombreCol;

    @FXML
    private TableColumn<Paciente, String> suscripcionCol;

    @FXML
    private TableView<Paciente> tablaPacientes;

    @FXML
    private TableColumn<Paciente, String> telefonoCol;

    private final ObservableList<Paciente> pacientes = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        configurarColumnas();
        actualizarTabla();
    }

    public void configurarColumnas() {
        idCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nombreCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        telefonoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        correoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        suscripcionCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getSuscripcion().getTipo()));
    }

    public void actualizarTabla() {
        pacientes.setAll(ControladorPrincipal.getInstancia().getClinica().getPacientes());
        tablaPacientes.setItems(pacientes);
        tablaPacientes.refresh();
    }
}
