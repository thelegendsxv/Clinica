package co.edu.uniquindio.clinica.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListaPacientesControlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> correoCol;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nombreCol;

    @FXML
    private TableColumn<?, ?> suscripcionCol;

    @FXML
    private TableView<?> tablaPacientes;

    @FXML
    private TableColumn<?, ?> telefonoCol;

    @FXML
    void initialize() {
        assert correoCol != null : "fx:id=\"correoCol\" was not injected: check your FXML file 'listaPacientes.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'listaPacientes.fxml'.";
        assert nombreCol != null : "fx:id=\"nombreCol\" was not injected: check your FXML file 'listaPacientes.fxml'.";
        assert suscripcionCol != null : "fx:id=\"suscripcionCol\" was not injected: check your FXML file 'listaPacientes.fxml'.";
        assert tablaPacientes != null : "fx:id=\"tablaPacientes\" was not injected: check your FXML file 'listaPacientes.fxml'.";
        assert telefonoCol != null : "fx:id=\"telefonoCol\" was not injected: check your FXML file 'listaPacientes.fxml'.";

    }

}
