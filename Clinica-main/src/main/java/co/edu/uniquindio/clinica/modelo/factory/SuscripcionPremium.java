package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

import java.util.ArrayList;
import java.util.List;

public class SuscripcionPremium implements Suscripcion {

    private List<Servicio> serviciosGratuitos = new ArrayList<>();

    @Override
    public void getFactura(Servicio servicio) {
        System.out.println("Factura:");
        System.out.println("Servicio: " + servicio.getNombre());
        System.out.println("Precio Final: $" + calcularTotal(servicio));
    }

    @Override
    public List<Servicio> getServiciosDisponibles() {
        return serviciosGratuitos;
    }

    @Override
    public double calcularTotal(Servicio servicio) {
        for (Servicio s : serviciosGratuitos) {
            if (s.getId().equals(servicio.getId())) {
                return 0.0; // Si está en gratuitos, no se cobra
            }
        }
        return servicio.getPrecio(); // Si no está, precio completo
    }

    // Método adicional para agregar servicios gratuitos
    public void agregarServicioGratuito(Servicio servicio) {
        serviciosGratuitos.add(servicio);
    }
}