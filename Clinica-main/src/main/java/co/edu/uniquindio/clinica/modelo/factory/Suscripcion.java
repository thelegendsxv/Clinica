package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.Servicio;

import java.util.List;

public interface Suscripcion {

    public List<Servicio> getServiciosDisponibles();
    public void getFactura(Servicio servicio);
    public double calcularTotal(Servicio servicio);
}
