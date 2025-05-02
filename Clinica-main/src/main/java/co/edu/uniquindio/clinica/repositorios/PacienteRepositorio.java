package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.servicios.PacienteServicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class PacienteRepositorio {
    private final List<Paciente> pacientes;

    public PacienteRepositorio() {
        this.pacientes = new ArrayList<>();
    }

    /*
    /Metodos
     */
    public Paciente buscarPacientePorId(String id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }

    public boolean existePaciente(String id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }
}
