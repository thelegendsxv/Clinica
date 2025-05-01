package co.edu.uniquindio.clinica.factory;

import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.modelo.entidades.ServicioSuscripcion;
import co.edu.uniquindio.clinica.modelo.enumer.TipoDescuento;

import java.util.ArrayList;
import java.util.List;

public class SuscripcionPremium implements Suscripcion {

    private List<ServicioSuscripcion> serviciosDisponibles = new ArrayList<>();

    @Override
    public List<ServicioSuscripcion> getServiciosDisponibles() {
        return serviciosDisponibles;
    }

    @Override
    public Factura generarFacturaCobro(Servicio servicio, String nombre) {
        double subtotal = servicio.getPrecio();
        double total = calcularTotal(servicio);

        ServicioSuscripcion servicioSuscripcion = null;

        servicioSuscripcion = buscarServicioSuscripcion(servicio);
        TipoDescuento tipo = (servicioSuscripcion != null) ? servicioSuscripcion.getTipoDescuento() : null;

        Factura factura = Factura.builder()
                .pacienteNombre(nombre)
                .servicio(servicio.getNombre())
                .subtotal(subtotal)
                .total(total)
                .build();
        return factura;
    }

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

    //Generalizado
    private ServicioSuscripcion buscarServicioSuscripcion(Servicio servicio) {
        for (ServicioSuscripcion ss : serviciosDisponibles) {
            if (ss.getServicio().equals(servicio)) {
                return ss;
            }
        }
        return null;
    }


}