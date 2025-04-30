package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.Servicio;
import co.edu.uniquindio.clinica.modelo.ServicioSuscripcion;

import java.util.List;

public interface Suscripcion {

    public List<ServicioSuscripcion> getServiciosDisponibles();
    public void getFactura(Servicio servicio);
    public double calcularTotal(Servicio servicio);
}
