package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

//Abstarct Factory Method
public abstract class SuscripcionFactory{
    public abstract Suscripcion crearSuscripcion();
    public double calcularTotal(Servicio servicio, TipoDescuento tipoDescuento)


}
