package co.edu.uniquindio.clinica.factory;

public class SuscripcionPremiumFactory extends SuscripcionFactory {

    @Override
    public Suscripcion crearSuscripcion() {
        return new SuscripcionPremium();
    }
}