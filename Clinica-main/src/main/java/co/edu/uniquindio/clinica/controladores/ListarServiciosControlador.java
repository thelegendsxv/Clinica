package co.edu.uniquindio.clinica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListarServiciosControlador {

    @FXML
    private TableView<Servicio> tbServicios;

    @FXML
    private TableColumn<Servicio, String> clNombre;

    @FXML
    private TableColumn<Servicio, Double> clPrecio;

    @FXML
    public void initialize() {
        // Configurar las columnas
        clNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        clPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // Simular datos
        ObservableList<Servicio> servicios = FXCollections.observableArrayList(
                new Servicio("Consulta general", 50000),
                new Servicio("Odontología", 80000)
        );

        tbServicios.setItems(servicios);
    }

    // Clase interna para ejemplo (idealmente va en su propio archivo)
    public static class Servicio {
        private final String nombre;
        private final double precio;

        public Servicio(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }
    }
}
