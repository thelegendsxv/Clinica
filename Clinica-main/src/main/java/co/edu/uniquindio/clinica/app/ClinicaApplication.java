package co.edu.uniquindio.clinica.app;

import co.edu.uniquindio.clinica.factory.*;
import co.edu.uniquindio.clinica.modelo.entidades.Clinica;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.Objects;

public class ClinicaApplication extends Application {

    public void datos() {
        try {
            Clinica clinica = new Clinica();

            SuscripcionFactory suscripcionFactory = new SuscripcionBasicaFactory();
            SuscripcionFactory suscripcionFactory2 = new SuscripcionPremiumFactory();
            Suscripcion suscripcionPremium = suscripcionFactory2.crearSuscripcion();
            Suscripcion suscripcionBasica = suscripcionFactory.crearSuscripcion();

            // Agregar pacientes
            clinica.agregarPaciente("123", "Santiago Torres", "3216549870", "santiago@mail.com", suscripcionPremium);
            clinica.agregarPaciente("124", "Daiana Ramirez", "3216549871", "daiana@mail.com", suscripcionBasica); // ahora con suscripción básica

            // Agregar servicios
            clinica.agregarServicio("Consulta General", 80000);
            clinica.agregarServicio("Odontología", 120000);
            clinica.agregarServicio("Terapia Física", 100000);

            // Obtener paciente y servicio para agendar cita
            Paciente paciente = clinica.getPacientes().get(0); // Santiago con premium
            Servicio servicio = clinica.getServicios().get(0); // Consulta General

            // Agendar cita
            LocalDateTime fechaCita = LocalDateTime.now().plusDays(2).withHour(10).withMinute(30);
            clinica.agendarCita(paciente, servicio, fechaCita);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(ClinicaApplication.class.getResource("/co/edu/uniquindio/clinica/panel.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        stage.getIcons().add(new Image(Objects.requireNonNull(ClinicaApplication.class.getResourceAsStream("/Img/medicamento.png"))));
        stage.setScene(scene);
        stage.setTitle("Clinica");
        stage.setResizable(true);
        stage.show();
    }
}
