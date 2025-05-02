package co.edu.uniquindio.clinica.factory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//Abstarct Factory Method
public abstract class SuscripcionFactory{
    public abstract Suscripcion crearSuscripcion();


}
