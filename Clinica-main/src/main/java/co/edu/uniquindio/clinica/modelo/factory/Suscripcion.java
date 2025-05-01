package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.*;

import java.util.List;

public interface Suscripcion {

    public List<ServicioSuscripcion> getServiciosDisponibles();
    public Factura generarFacturaCobro(Servicio servicio, String nombre);
    public double calcularTotal(Servicio servicio);
}
