package co.edu.uniquindio.clinica.suscripcion;

import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.modelo.enumer.TipoDescuento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ServicioSuscripcion {

    private Servicio servicio;
    private TipoDescuento tipoDescuento;


}

