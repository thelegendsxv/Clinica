package co.edu.uniquindio.clinica.suscripcion;

import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;

import java.util.Collections;
import java.util.List;

public class SinSuscripcion implements Suscripcion {

    @Override
    public List<ServicioSuscripcion> getServiciosDisponibles() {
        return Collections.emptyList();
    }

    @Override
    public Factura generarFacturaCobro(Servicio servicio, String nombre) {
        double subtotal = servicio.getPrecio();
        double total = calcularTotal(servicio);

        return Factura.builder()
                .pacienteNombre(nombre)
                .servicio(servicio.getNombre())
                .subtotal(subtotal)
                .total(total)
                .build();
    }

    @Override
    public double calcularTotal(Servicio servicio) {
        recomendarCambio();
        return servicio.getPrecio();
    }

    @Override
    public String getTipo() {
        return "Sin Suscripcion";
    }

    public void recomendarCambio() {
        System.out.println("Sin suscripción: se cobra el valor completo, considera suscribirte para obtener descuentos en tus servicios.");

    }
}