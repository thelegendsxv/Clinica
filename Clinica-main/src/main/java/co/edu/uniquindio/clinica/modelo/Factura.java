package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.enumer.TipoDescuento;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
public class Factura {
    private LocalDate fecha;
    private String pacienteNombre;
    private String servicio;
    private String id;
    private double total;
    private double subtotal;
    private TipoDescuento tipo;
}
