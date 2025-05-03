package co.edu.uniquindio.clinica.modelo.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Builder
@Getter
public class Servicio {
    private String nombre;
    private String id;
    private double precio;

    @Override
    public String toString() {
        return nombre + " - $" + String.format("%,.0f", precio);
    }
}
