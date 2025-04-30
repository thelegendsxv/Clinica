package co.edu.uniquindio.clinica.modelo.factory;

public class SuscripcionBasicaFactory extends SuscripcionFactory {

    @Override
    public Suscripcion crearSuscripcion() {
        return new SuscripcionBasica();
    }



}
