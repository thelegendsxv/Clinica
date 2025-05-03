package co.edu.uniquindio.clinica.modelo.entidades;

import co.edu.uniquindio.clinica.modelo.enumer.EstadoCita;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Cita {
    private String id;
    private LocalDateTime fecha;
    private EstadoCita estado;
    private Paciente paciente;
    private Servicio servicio;
    private Factura factura;
    private String notas;

}
