package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.NumberFormat;
import java.util.Locale;

public class ListarServiciosControlador {

    @FXML
    private TableView<Servicio> tbServicios;

    @FXML
    private TableColumn<Servicio, String> clNombre;

    @FXML
    private TableColumn<Servicio, String> clPrecio;

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarDatos();
    }

    private void configurarColumnas() {
        clNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));

        // Formatear el precio como moneda
        clPrecio.setCellValueFactory(c -> {
            NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
            return new SimpleStringProperty(formatoMoneda.format(c.getValue().getPrecio()));
        });
    }

    private void cargarDatos() {
        tbServicios.setItems(FXCollections.observableArrayList(
                ControladorPrincipal.getInstancia().getClinica().getServicios()
        ));
    }
}