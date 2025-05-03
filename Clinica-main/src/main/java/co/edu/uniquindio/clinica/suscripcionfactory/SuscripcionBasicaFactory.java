package co.edu.uniquindio.clinica.suscripcionfactory;

import co.edu.uniquindio.clinica.suscripcion.Suscripcion;
import co.edu.uniquindio.clinica.suscripcion.SuscripcionBasica;

public class SuscripcionBasicaFactory extends SuscripcionFactory {

    @Override
    public Suscripcion crearSuscripcion() {
        return new SuscripcionBasica();
    }



}
