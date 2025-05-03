package co.edu.uniquindio.clinica.suscripcion;

import co.edu.uniquindio.clinica.modelo.entidades.Factura;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.modelo.enumer.TipoDescuento;
import co.edu.uniquindio.clinica.modelo.enumer.TipoSuscripcion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.ArrayList;  // Importar ArrayList para inicializar la lista

@Getter
@Setter
@ToString

public class SuscripcionBasica implements Suscripcion {

    private final List<ServicioSuscripcion> serviciosDisponibles = List.of(
            new ServicioSuscripcion(new Servicio("Consulta General", 80000, "S001"), TipoDescuento.INCOMPLETO, TipoSuscripcion.BASICA),
            new ServicioSuscripcion(new Servicio("Laboratorio Clínico", 70000, "S004"), TipoDescuento.INCOMPLETO, TipoSuscripcion.BASICA),
            new ServicioSuscripcion(new Servicio("Radiografía", 95000, "S005"), TipoDescuento.INCOMPLETO, TipoSuscripcion.BASICA),
            new ServicioSuscripcion(new Servicio("Nutrición", 75000, "S009"), TipoDescuento.INCOMPLETO, TipoSuscripcion.BASICA),
            new ServicioSuscripcion(new Servicio("Dermatología", 115000, "S011"), TipoDescuento.COMPLETO, TipoSuscripcion.BASICA),
            new ServicioSuscripcion(new Servicio("Vacunación", 60000, "S013"), TipoDescuento.INCOMPLETO, TipoSuscripcion.BASICA),
            new ServicioSuscripcion(new Servicio("Chequeo Médico General", 90000, "S014"), TipoDescuento.INCOMPLETO, TipoSuscripcion.BASICA),
            new ServicioSuscripcion(new Servicio("Consulta Pediátrica", 85000, "S007"), TipoDescuento.COMPLETO, TipoSuscripcion.BASICA),
            new ServicioSuscripcion(new Servicio("Oftalmología", 95000, "S015"), TipoDescuento.INCOMPLETO, TipoSuscripcion.BASICA)
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

    @Override
    public double calcularTotal(Servicio servicio) {

        double precioFinal = servicio.getPrecio();

        ServicioSuscripcion servicioSuscripcionBuscado = null;

        servicioSuscripcionBuscado = buscarServicioSuscripcion(servicio);

        if(servicioSuscripcionBuscado == null) {
            precioFinal = servicio.getPrecio();
        }else{
            if (servicioSuscripcionBuscado.getTipoDescuento() == TipoDescuento.COMPLETO) {
                precioFinal = 0;
            }
            else if (servicioSuscripcionBuscado.getTipoDescuento()  == TipoDescuento.INCOMPLETO) {
                precioFinal = servicio.getPrecio() * 0.30;
            }
        }

        return precioFinal;
    }



    public void agregarServicio(Servicio servicio, TipoDescuento tipo) throws Exception {
        serviciosDisponibles.add(new ServicioSuscripcion(servicio, tipo, TipoSuscripcion.BASICA));
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
        return "Basica";  // Devuelve el tipo de suscripción

    }
}