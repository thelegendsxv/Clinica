package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.enumer.TipoDescuento;
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

}
