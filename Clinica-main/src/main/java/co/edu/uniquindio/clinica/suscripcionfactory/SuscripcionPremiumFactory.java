package co.edu.uniquindio.clinica.suscripcionfactory;

import co.edu.uniquindio.clinica.suscripcion.Suscripcion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class SuscripcionPremiumFactory extends SuscripcionFactory {

    @Override
    public Suscripcion crearSuscripcion() {
        return new SuscripcionPremium();
    }
}