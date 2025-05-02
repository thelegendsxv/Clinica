package co.edu.uniquindio.clinica.modelo.entidades;

public class ClinicaSingleton {
    private static final Clinica instancia = new Clinica();

    private ClinicaSingleton() {}

    public static Clinica getInstancia() {
        return instancia;
    }
}
