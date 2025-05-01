package co.edu.uniquindio.clinica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CrearPacienteControlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> boxTipoSuscripcion;

    @FXML
    private TextField correoTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField telefonoTextField;

    @FXML
    void crearPaciente(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxTipoSuscripcion != null : "fx:id=\"boxTipoSuscripcion\" was not injected: check your FXML file 'crearPaciente.fxml'.";
        assert correoTextField != null : "fx:id=\"correoTextField\" was not injected: check your FXML file 'crearPaciente.fxml'.";
        assert idTextField != null : "fx:id=\"idTextField\" was not injected: check your FXML file 'crearPaciente.fxml'.";
        assert nombreTextField != null : "fx:id=\"nombreTextField\" was not injected: check your FXML file 'crearPaciente.fxml'.";
        assert telefonoTextField != null : "fx:id=\"telefonoTextField\" was not injected: check your FXML file 'crearPaciente.fxml'.";

    }

}

