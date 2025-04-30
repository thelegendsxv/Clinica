package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Servicio;
import co.edu.uniquindio.clinica.modelo.ServicioSuscripcion;
import co.edu.uniquindio.clinica.modelo.enumer.TipoDescuento;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class SuscripcionPremium implements Suscripcion {

    private List<ServicioSuscripcion> serviciosDisponibles = new ArrayList<>();

    @Override
    public List<ServicioSuscripcion> getServiciosDisponibles() {
        return serviciosDisponibles;
    }

    @Override
    public Factura getFactura(Servicio servicio) {


        return factura;
    }

    @Override
    public double calcularTotal(Servicio servicio ) {




        double precioFinal = servicio.getPrecio();

        ServicioSuscripcion servicioSuscripcionBuscado = null;

        for (ServicioSuscripcion servicioSuscripcion : serviciosDisponibles) {
            if(servicioSuscripcion.getServicio().equals(servicio)) {
                servicioSuscripcionBuscado = servicioSuscripcion;
            }
        }

        if(servicioSuscripcionBuscado == null) {
            precioFinal = servicio.getPrecio();
        }else{
            if (servicioSuscripcionBuscado.getTipoDescuento() == TipoDescuento.COMPLETO) {
                // Descuento completo: servicio gratuito
                System.out.println("Descuento Completo: El servicio es gratuito.");
                precioFinal = 0;
            }
            else if (servicioSuscripcionBuscado.getTipoDescuento()  == TipoDescuento.INCOMPLETO) {
                // Descuento incompleto: un porcentaje de descuento, por ejemplo, 50%
                System.out.println("Descuento Incompleto: Aplicando descuento del 50%");
                precioFinal = servicio.getPrecio() * 0.50;
            }
        }

        return precioFinal;

    }

    public void agregarServicio(Servicio servicio, TipoDescuento tipo) throws Exception {
        serviciosDisponibles.add(new ServicioSuscripcion(servicio, tipo));
    }

}