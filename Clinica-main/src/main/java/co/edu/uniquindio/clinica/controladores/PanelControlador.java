package co.edu.uniquindio.clinica.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PanelControlador implements Initializable {

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
    private TabPane tabPane;


    private void configuracionVentanas() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == botonCrearPaciente) {
                cargarContenido("/co/edu/uniquindio/clinica/crearPaciente.fxml", botonCrearPaciente);
            } else if (newTab == botonListarpaciente) {
                cargarContenido("/co/edu/uniquindio/clinica/listaPacientes.fxml", botonListarpaciente);
            } else if (newTab == botonCrearCita) {
                cargarContenido("/co/edu/uniquindio/clinica/crearCita.fxml", botonCrearCita);
            } else if (newTab == botonListarCita) {
                cargarContenido("/co/edu/uniquindio/clinica/listarCita.fxml", botonListarCita);
            }
        });
    }

    private void cargarContenido(String rutaFXML, Tab tab) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            tab.setContent(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configuracionVentanas();
    }
}



