package co.edu.uniquindio.clinica.modelo.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Servicio {
    private String nombre;
    private String id;
    private double precio;

    public Servicio(String nombre, double precio, String id) {
        this.nombre = nombre;
        this.precio = precio;
        this.id = id;
    }

    @Override
    public String toString() {
        return nombre + " - $" + String.format("%,.0f", precio);
    }
}
