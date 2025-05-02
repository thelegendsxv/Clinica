package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.factory.Suscripcion;
import co.edu.uniquindio.clinica.factory.SuscripcionBasicaFactory;
import co.edu.uniquindio.clinica.factory.SuscripcionFactory;
import co.edu.uniquindio.clinica.factory.SuscripcionPremiumFactory;
import co.edu.uniquindio.clinica.modelo.entidades.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CrearPacienteControlador {

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

    // Método setter para recibir la referencia del controlador de ListaPacientes
    public void setListaPacientesControlador(ListaPacientesControlador controlador) {
        this.listaPacientesControlador = controlador;
    }

    @FXML
    void crearPaciente(ActionEvent event) {
        try {
            // Obtener datos del formulario
            String id = idTextField.getText();
            String nombre = nombreTextField.getText();
            String telefono = telefonoTextField.getText();
            String correo = correoTextField.getText();
            String tipo = tipoSuscripcion.getValue();

            // Validación de campos vacíos
            if (id.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty() || tipo == null) {
                throw new IllegalArgumentException("Todos los campos deben estar llenos");
            }

            // Usar las fábricas según el tipo de suscripción
            SuscripcionFactory suscripcionFactory;

            if (tipo.equals("Básica")) {
                suscripcionFactory = new SuscripcionBasicaFactory();
            } else if (tipo.equals("Premium")) {
                suscripcionFactory = new SuscripcionPremiumFactory();
            } else {
                throw new IllegalArgumentException("Tipo de suscripción no válido");
            }

            // Crear la suscripción mediante la fábrica
            Suscripcion suscripcion = suscripcionFactory.crearSuscripcion();

            // Crear paciente
            Clinica clinica = ClinicaSingleton.getInstancia();
            Paciente paciente = clinica.agregarPaciente(id, nombre, telefono, correo, suscripcion);

            // Agregar el paciente al controlador de lista
            if (listaPacientesControlador != null) {
                listaPacientesControlador.agregarPaciente(paciente); // Actualizar la tabla
            }

            // Limpiar campos luego de agregar el paciente
            idTextField.clear();
            nombreTextField.clear();
            telefonoTextField.clear();
            correoTextField.clear();
            tipoSuscripcion.setValue(null);

        } catch (Exception e) {
            e.printStackTrace();
            // Aquí puedes mostrar un Alert dialog en lugar de solo imprimir
        }
    }

    @FXML
    void initialize() {
        tipoSuscripcion.getItems().addAll("Básica", "Premium");
    }
}
