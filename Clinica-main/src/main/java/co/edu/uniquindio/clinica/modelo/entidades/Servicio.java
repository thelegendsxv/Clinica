package co.edu.uniquindio.clinica.modelo.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Builder
@Getter
@ToString
public class Servicio {
    private String nombre;
    private String id;
    private double precio;

}