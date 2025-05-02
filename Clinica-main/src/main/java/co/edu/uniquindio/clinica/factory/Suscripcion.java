package co.edu.uniquindio.clinica.factory;

import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.modelo.entidades.ServicioSuscripcion;

import java.util.List;

public interface Suscripcion {

    public List<ServicioSuscripcion> getServiciosDisponibles();
    public Factura generarFacturaCobro(Servicio servicio, String nombre);
    public double calcularTotal(Servicio servicio);
    public String getTipo();  // Este es el nuevo método

}
