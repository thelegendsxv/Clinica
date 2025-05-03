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
    private List<ServicioSuscripcion> serviciosDisponibles;

    // Constructor que inicializa la lista de servicios
    public SuscripcionBasica() {
        serviciosDisponibles = new ArrayList<>();
    }

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