package co.edu.uniquindio.clinica.modelo.enumer;

import co.edu.uniquindio.clinica.suscripcion.SinSuscripcion;

public enum TipoSuscripcion {
    BASICA("Básica"),
    PREMIUM("Premium"),
    SINSUSCRIPCION("Sin suscripcion");

    private final String nombre;

    TipoSuscripcion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}