package co.edu.uniquindio.clinica.controladores;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.modelo.enumer.EstadoCita;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListarCitaControlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Cita, EstadoCita> TablaEstado;

    @FXML
    private TableColumn<Cita, LocalDateTime> TablaFecha;

    @FXML
    private TableColumn<Cita, String> TablaId;

    @FXML
    private TableColumn<Cita, Paciente> TablaPaciente;

    @FXML
    private TableColumn<Cita, Servicio> TablaServicio;

    @FXML
    private Label TextListaDeCitas;

    @FXML
    private TableView<?> tablaCitas;

    @FXML
    void initialize() {
        assert TablaEstado != null : "fx:id=\"TablaEstado\" was not injected: check your FXML file 'listarCita.fxml'.";
        assert TablaFecha != null : "fx:id=\"TablaFecha\" was not injected: check your FXML file 'listarCita.fxml'.";
        assert TablaId != null : "fx:id=\"TablaId\" was not injected: check your FXML file 'listarCita.fxml'.";
        assert TablaPaciente != null : "fx:id=\"TablaPaciente\" was not injected: check your FXML file 'listarCita.fxml'.";
        assert TablaServicio != null : "fx:id=\"TablaServicio\" was not injected: check your FXML file 'listarCita.fxml'.";
        assert TextListaDeCitas != null : "fx:id=\"TextListaDeCitas\" was not injected: check your FXML file 'listarCita.fxml'.";
        assert tablaCitas != null : "fx:id=\"tablaCitas\" was not injected: check your FXML file 'listarCita.fxml'.";

    }

}
