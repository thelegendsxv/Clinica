package co.edu.uniquindio.clinica.modelo.entidades;

import co.edu.uniquindio.clinica.factory.Suscripcion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
public class Paciente {
    private String id;
    private String nombre;
    private String telefono;
    private String correo;
    private Suscripcion suscripcion;

}