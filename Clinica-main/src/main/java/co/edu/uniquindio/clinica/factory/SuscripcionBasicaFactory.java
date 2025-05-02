package co.edu.uniquindio.clinica.factory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SuscripcionBasicaFactory extends SuscripcionFactory {

    @Override
    public Suscripcion crearSuscripcion() {
        return new SuscripcionBasica();
    }



}
