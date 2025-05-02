package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CitaRepositorio {
    private List<Cita> citas;

    public CitaRepositorio() {
        this.citas = new ArrayList<>();
    }

    public void agregarCita(Cita cita) {
        this.citas.add(cita);
    }

    public Cita buscarCitaPorId(String id) {
        for (Cita cita : citas) {
            if (cita.getId().equals(id)) {
                return cita;
            }
        }
        return null;
    }
}
