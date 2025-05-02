package co.edu.uniquindio.clinica.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class PanelControlador {

    @FXML
    private ImageView Image;

    @FXML
    private Label textPanelClinica;

    @FXML
    private StackPane panelContenido;

    @FXML
    public void mostrarCrearPaciente() {
        cargarVista("/co/edu/uniquindio/clinica/crearPaciente.fxml");
    }

    @FXML
    public void mostrarListarPacientes() {
        cargarVista("/co/edu/uniquindio/clinica/listaPacientes.fxml");
    }

    @FXML
    public void mostrarCrearCita() {
        cargarVista("/co/edu/uniquindio/clinica/crearCita.fxml");
    }

    @FXML
    public void mostrarListarCitas() {
        cargarVista("/co/edu/uniquindio/clinica/listarCita.fxml");
    }

    private void cargarVista(String ruta) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent vista = loader.load();
            panelContenido.getChildren().setAll(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
