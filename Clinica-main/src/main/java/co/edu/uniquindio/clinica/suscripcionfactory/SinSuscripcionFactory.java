package co.edu.uniquindio.clinica.suscripcionfactory;

import co.edu.uniquindio.clinica.suscripcion.SinSuscripcion;
import co.edu.uniquindio.clinica.suscripcion.Suscripcion;
import co.edu.uniquindio.clinica.suscripcion.SuscripcionBasica;

public class SinSuscripcionFactory extends SuscripcionFactory {
    @Override
    public Suscripcion crearSuscripcion() {
        return new SinSuscripcion();
    }

}
