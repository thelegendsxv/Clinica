package co.edu.uniquindio.clinica.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinica.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import javafx.beans.property.SimpleObjectProperty;
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
    private TableColumn<Paciente, Suscripcion> suscripcionCol;

    @FXML
    private TableView<Paciente> tablaPacientes;

    @FXML
    private TableColumn<Paciente, String> telefonoCol;

    private ObservableList<Paciente> pacientes = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        tablaPacientes.setItems(pacientes); // Asociar la lista de pacientes a la tabla

        // Aquí puedes establecer los valores de las columnas de la tabla
        idCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nombreCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        telefonoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        correoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        suscripcionCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSuscripcion()));
    }

    // Método para agregar pacientes a la lista
    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }
}
