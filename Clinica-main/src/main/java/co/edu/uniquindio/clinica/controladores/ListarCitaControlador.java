package co.edu.uniquindio.clinica.controladores;

import java.net.URL;
import java.util.ResourceBundle;
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
    private Label ListaDeCitas;

    @FXML
    private TableColumn<?, ?> TablaEstadoCol;

    @FXML
    private TableColumn<?, ?> TablaFechaCol;

    @FXML
    private TableColumn<?, ?> TablaHoraCol;

    @FXML
    private TableColumn<?, ?> TablaIdCol;

    @FXML
    private TableColumn<?, ?> TablaPacienteCol;

    @FXML
    private TableColumn<?, ?> TablaServicioCol;

    @FXML
    private TableView<?> tablaCitas;

    @FXML
    void initialize() {
        assert ListaDeCitas != null : "fx:id=\"ListaDeCitas\" was not injected: check your FXML file 'listarCita.fxml'.";
        assert TablaEstadoCol != null : "fx:id=\"TablaEstadoCol\" was not injected: check your FXML file 'listarCita.fxml'.";
        assert TablaFechaCol != null : "fx:id=\"TablaFechaCol\" was not injected: check your FXML file 'listarCita.fxml'.";
        assert TablaHoraCol != null : "fx:id=\"TablaHoraCol\" was not injected: check your FXML file 'listarCita.fxml'.";
        assert TablaIdCol != null : "fx:id=\"TablaIdCol\" was not injected: check your FXML file 'listarCita.fxml'.";
        assert TablaPacienteCol != null : "fx:id=\"TablaPacienteCol\" was not injected: check your FXML file 'listarCita.fxml'.";
        assert TablaServicioCol != null : "fx:id=\"TablaServicioCol\" was not injected: check your FXML file 'listarCita.fxml'.";
        assert tablaCitas != null : "fx:id=\"tablaCitas\" was not injected: check your FXML file 'listarCita.fxml'.";

    }

}
