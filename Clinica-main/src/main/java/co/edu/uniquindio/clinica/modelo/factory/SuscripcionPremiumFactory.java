package co.edu.uniquindio.clinica.modelo.factory;

public class SuscripcionPremiumFactory extends SuscripcionFactory {

    @Override
    public Suscripcion crearSuscripcion() {
        return new SuscripcionPremium();
    }
}