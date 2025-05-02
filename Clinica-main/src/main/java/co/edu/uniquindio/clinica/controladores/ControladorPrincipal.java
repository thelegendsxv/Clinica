package co.edu.uniquindio.clinica.controladores;


import co.edu.uniquindio.clinica.modelo.entidades.Clinica;
import co.edu.uniquindio.clinica.servicios.ClinicaServicio;


import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;
import javafx.scene.Node;


public class ControladorPrincipal {


    private static ControladorPrincipal instancia;

    @Getter
    private Clinica clinica;

    private ControladorPrincipal() {
    }

    public static ControladorPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }

    public void inicializarConClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }


    public void crearAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }



}
