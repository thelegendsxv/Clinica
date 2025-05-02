package co.edu.uniquindio.clinica.controladores;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CrearCitaControlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BotonCrearCita;

    @FXML
    private TextField IngreseNotas;

    @FXML
    private DatePicker SeleccioneFecha;

    @FXML
    private ComboBox<LocalDateTime> SeleccioneHora;

    @FXML
    private ComboBox<Paciente> SeleccionePaciente;

    @FXML
    private ComboBox<Servicio> SeleccioneServicio;

    @FXML
    private Text TextCrearCita;

    @FXML
    private Text TextFecha;

    @FXML
    private Text TextHora;

    @FXML
    private Text TextNota;

    @FXML
    private Text TextPaciente;

    @FXML
    private Text TextServicio;

    @FXML
    void crearCita(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert BotonCrearCita != null : "fx:id=\"BotonCrearCita\" was not injected: check your FXML file 'crearCita.fxml'.";
        assert IngreseNotas != null : "fx:id=\"IngreseNotas\" was not injected: check your FXML file 'crearCita.fxml'.";
        assert SeleccioneFecha != null : "fx:id=\"SeleccioneFecha\" was not injected: check your FXML file 'crearCita.fxml'.";
        assert SeleccioneHora != null : "fx:id=\"SeleccioneHora\" was not injected: check your FXML file 'crearCita.fxml'.";
        assert SeleccionePaciente != null : "fx:id=\"SeleccionePaciente\" was not injected: check your FXML file 'crearCita.fxml'.";
        assert SeleccioneServicio != null : "fx:id=\"SeleccioneServicio\" was not injected: check your FXML file 'crearCita.fxml'.";
        assert TextCrearCita != null : "fx:id=\"TextCrearCita\" was not injected: check your FXML file 'crearCita.fxml'.";
        assert TextFecha != null : "fx:id=\"TextFecha\" was not injected: check your FXML file 'crearCita.fxml'.";
        assert TextHora != null : "fx:id=\"TextHora\" was not injected: check your FXML file 'crearCita.fxml'.";
        assert TextNota != null : "fx:id=\"TextNota\" was not injected: check your FXML file 'crearCita.fxml'.";
        assert TextPaciente != null : "fx:id=\"TextPaciente\" was not injected: check your FXML file 'crearCita.fxml'.";
        assert TextServicio != null : "fx:id=\"TextServicio\" was not injected: check your FXML file 'crearCita.fxml'.";

    }

}
