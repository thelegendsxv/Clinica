package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Paciente {
    private String id;
    private String nombre;
    private String telefono;
    private String correo;
    private Suscripcion suscripcion;

}
