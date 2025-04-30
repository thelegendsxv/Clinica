package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

import java.util.List;
import java.util.ArrayList;  // Importar ArrayList para inicializar la lista

public class SuscripcionBasica implements Suscripcion {
    private List<Servicio> serviciosDisponibles;  // Lista de servicios disponibles

    // Constructor que inicializa la lista de servicios
    public SuscripcionBasica() {
        serviciosDisponibles = new ArrayList<>();  // Inicializamos la lista
    }

    @Override
    public List<Servicio> getServiciosDisponibles() {
        return serviciosDisponibles;
    }

    @Override
    public void getFactura(Servicio servicio) {
        System.out.println("Factura:");
        System.out.println("Servicio: " + servicio.getNombre());
        System.out.println("Precio: $" + servicio.getPrecio());
        serviciosDisponibles.add(servicio);  // Agregar el servicio a la lista
    }

    @Override
    public double calcularTotal(Servicio servicio) {
        double precioFinal = servicio.getPrecio();

        if (tipoDescuento == TipoDescuento.COMPLETO) {
            // Descuento completo: servicio gratuito
            System.out.println("Descuento Completo: El servicio es gratuito.");
            precioFinal = 0;
        }
        else if (tipoDescuento == TipoDescuento.INCOMPLETO) {
            // Descuento incompleto: un porcentaje de descuento, por ejemplo, 50%
            System.out.println("Descuento Incompleto: Aplicando descuento del 50%");
            precioFinal = servicio.getPrecio() * 0.50;
        }
        else if (tipoDescuento == TipoDescuento.NO) {
            // Sin descuento
            System.out.println("Sin descuento: El precio es el original.");
        }
        else {
            System.out.println("Tipo de descuento no reconocido.");
        }

        return precioFinal;    }



    public void agregarServicio(String nombre, double precio) throws Exception {
        validarServicio(nombre);  // Verifica que el servicio no exista

        // Creamos un nuevo servicio y lo agregamos a la lista de servicios
        Servicio servicio = Servicio.builder()
                .nombre(nombre)
                .precio(precio)
                .build();

        serviciosDisponibles.add(servicio);  // Agregar el servicio a la lista
    }

    private void validarServicio(String nombre) throws Exception {
        // Verifica si el servicio ya existe
        for (Servicio servicio : serviciosDisponibles) {
            if (servicio.getNombre().equalsIgnoreCase(nombre)) {
                throw new Exception("El servicio ya existe.");
            }
        }

        // Verifica si el nombre del servicio no es nulo o vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El servicio no puede ser nulo o vacío.");
        }
    }
}