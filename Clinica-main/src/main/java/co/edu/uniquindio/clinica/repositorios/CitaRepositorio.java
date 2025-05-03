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
        if (cita == null) {
            throw new IllegalArgumentException("La cita no puede ser nula");
        }
        this.citas.add(cita);
    }

    public List<Cita> getCitas() {
        return new ArrayList<>(citas); // Retorna copia para evitar modificaciones externas
    }

    public Cita buscarCitaPorId(String id) {
        return citas.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}