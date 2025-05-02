package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.factory.Suscripcion;
import co.edu.uniquindio.clinica.factory.SuscripcionBasicaFactory;
import co.edu.uniquindio.clinica.factory.SuscripcionFactory;
import co.edu.uniquindio.clinica.factory.SuscripcionPremiumFactory;
import co.edu.uniquindio.clinica.modelo.entidades.*;
import co.edu.uniquindio.clinica.servicios.ClinicaServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CrearPacienteControlador {
    private final ClinicaServicio clinica = ControladorPrincipal.getInstancia().getClinica();

    private ListaPacientesControlador listaPacientesControlador;

    @FXML
    private ComboBox<String> tipoSuscripcion;

    @FXML
    private TextField correoTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField telefonoTextField;

    public void setListaPacientesControlador(ListaPacientesControlador controlador) {
        this.listaPacientesControlador = controlador;
    }

    @FXML
    void crearPaciente(ActionEvent event) {
        try {
            String id = idTextField.getText();
            String nombre = nombreTextField.getText();
            String telefono = telefonoTextField.getText();
            String correo = correoTextField.getText();
            String tipo = tipoSuscripcion.getValue();
            validarCampos(id, nombre, telefono, correo, tipo);

            SuscripcionFactory suscripcionFactory = tipo.equals("Básica") ?
                    new SuscripcionBasicaFactory() : new SuscripcionPremiumFactory();

            Suscripcion suscripcion = suscripcionFactory.crearSuscripcion();

            clinica.registrarPaciente(id, nombre, telefono, correo, suscripcion);

            if (listaPacientesControlador != null) {
                listaPacientesControlador.actualizarTabla(); // <- Corrección clave
            }

            limpiarCampos();
            mostrarAlerta("Éxito", "Paciente creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo crear el paciente: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        idTextField.clear();
        nombreTextField.clear();
        telefonoTextField.clear();
        correoTextField.clear();
        tipoSuscripcion.setValue(null);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void validarCampos(String id, String nombre, String telefono, String correo, String tipo){
        if (id.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty() || tipo == null) {
            mostrarAlerta("Campos incompletos", "Todos los campos deben estar llenos.");
            return;
        }

        if (!telefono.matches("\\d+")) {
            mostrarAlerta("Teléfono inválido", "El teléfono solo debe contener números.");
            return;
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            mostrarAlerta("Correo inválido", "El correo debe contener un '@' y un dominio.");
            return;
        }

    }

    @FXML
    void initialize() {
        tipoSuscripcion.getItems().addAll("Básica", "Premium");
    }
}
