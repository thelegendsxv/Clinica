package co.edu.uniquindio.clinica.suscripcion;

import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;

import java.util.List;

public interface Suscripcion {

    public List<ServicioSuscripcion> getServiciosDisponibles();
    public Factura generarFacturaCobro(Servicio servicio, String nombre);
    public double calcularTotal(Servicio servicio);
    public String getTipo();  // Este es el nuevo método

}
