package co.edu.uniquindio.clinica.suscripcion;

import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.modelo.enumer.TipoDescuento;
import co.edu.uniquindio.clinica.modelo.enumer.TipoSuscripcion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ToString

public class SuscripcionPremium implements Suscripcion {

    private final List<ServicioSuscripcion> serviciosDisponibles = List.of(
            new ServicioSuscripcion(new Servicio("Consulta General", 80000, "S001"), TipoDescuento.COMPLETO, TipoSuscripcion.PREMIUM),
            new ServicioSuscripcion(new Servicio("Odontología", 120000, "S002"), TipoDescuento.INCOMPLETO, TipoSuscripcion.PREMIUM),
            new ServicioSuscripcion(new Servicio("Terapia Física", 100000, "S003"), TipoDescuento.COMPLETO, TipoSuscripcion.PREMIUM),
            new ServicioSuscripcion(new Servicio("Ecografía", 110000, "S006"), TipoDescuento.COMPLETO, TipoSuscripcion.PREMIUM),
            new ServicioSuscripcion(new Servicio("Psicología", 90000, "S008"), TipoDescuento.COMPLETO, TipoSuscripcion.PREMIUM),
            new ServicioSuscripcion(new Servicio("Cardiología", 130000, "S010"), TipoDescuento.INCOMPLETO, TipoSuscripcion.PREMIUM),
            new ServicioSuscripcion(new Servicio("Ginecología", 125000, "S012"), TipoDescuento.COMPLETO, TipoSuscripcion.PREMIUM),
            new ServicioSuscripcion(new Servicio("Medicina Interna", 140000, "S016"), TipoDescuento.COMPLETO, TipoSuscripcion.PREMIUM),
            new ServicioSuscripcion(new Servicio("Neurología", 145000, "S018"), TipoDescuento.COMPLETO, TipoSuscripcion.PREMIUM),
            new ServicioSuscripcion(new Servicio("Endocrinología", 128000, "S020"), TipoDescuento.COMPLETO, TipoSuscripcion.PREMIUM)
    );

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
                precioFinal = 0;
            }
            else if (servicioSuscripcionBuscado.getTipoDescuento()  == TipoDescuento.INCOMPLETO) {

                precioFinal = servicio.getPrecio() * 0.70;
            }
        }

        return precioFinal;

    }

    public void agregarServicio(Servicio servicio, TipoDescuento tipo) throws Exception {
        serviciosDisponibles.add(new ServicioSuscripcion(servicio, tipo, TipoSuscripcion.PREMIUM));
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

    @Override
    public String getTipo() {
        return "Premium";  // Devuelve el tipo de suscripción
    }


}