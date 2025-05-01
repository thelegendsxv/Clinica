package co.edu.uniquindio.clinica.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;

public class PanelControlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView Image;

    @FXML
    private Tab botonCrearCita;

    @FXML
    private Tab botonCrearPaciente;

    @FXML
    private Tab botonListarCita;

    @FXML
    private Tab botonListarpaciente;

    @FXML
    private Label textPanelClinica;

    @FXML
    void initialize() {
        assert Image != null : "fx:id=\"Image\" was not injected: check your FXML file 'panel.fxml'.";
        assert botonCrearCita != null : "fx:id=\"botonCrearCita\" was not injected: check your FXML file 'panel.fxml'.";
        assert botonCrearPaciente != null : "fx:id=\"botonCrearPaciente\" was not injected: check your FXML file 'panel.fxml'.";
        assert botonListarCita != null : "fx:id=\"botonListarCita\" was not injected: check your FXML file 'panel.fxml'.";
        assert botonListarpaciente != null : "fx:id=\"botonListarpaciente\" was not injected: check your FXML file 'panel.fxml'.";
        assert textPanelClinica != null : "fx:id=\"textPanelClinica\" was not injected: check your FXML file 'panel.fxml'.";

    }

}
