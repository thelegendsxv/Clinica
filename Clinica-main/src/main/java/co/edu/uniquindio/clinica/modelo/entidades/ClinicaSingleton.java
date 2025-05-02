package co.edu.uniquindio.clinica.modelo.entidades;

import lombok.Getter;

public class ClinicaSingleton {
    @Getter
    private static final Clinica instancia = new Clinica();

    private ClinicaSingleton() {}

}