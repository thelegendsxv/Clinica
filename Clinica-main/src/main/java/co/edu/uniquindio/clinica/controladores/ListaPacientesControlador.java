package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.factory.Suscripcion;
import co.edu.uniquindio.clinica.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.factory.SuscripcionPremium;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
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
        tablaPacientes.setItems(pacientes);

        idCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nombreCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        telefonoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        correoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        suscripcionCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSuscripcion().getTipo()));
    }

    public void agregarPaciente(Paciente paciente) {
        if (paciente != null) {
            pacientes.add(paciente);
            tablaPacientes.setItems(pacientes);
            tablaPacientes.refresh();
        }
    }

    public void actualizarTabla() {
        tablaPacientes.refresh();
    }
}