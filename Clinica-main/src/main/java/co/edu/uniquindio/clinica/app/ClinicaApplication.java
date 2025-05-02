package co.edu.uniquindio.clinica.app;

import co.edu.uniquindio.clinica.factory.Suscripcion;
import co.edu.uniquindio.clinica.factory.SuscripcionBasicaFactory;
import co.edu.uniquindio.clinica.factory.SuscripcionFactory;
import co.edu.uniquindio.clinica.factory.SuscripcionPremiumFactory;
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
            clinica.agregarPaciente("123", "Santiago Torres", "3216549870", "santiago.rodriguezt@uqvirtual.edu.co", suscripcionPremium);
            clinica.agregarPaciente("124", "Daiana Ramirez", "911694991", "santiago.rodriguezt@uqvirtual.edu.co", suscripcionBasica);

            // Agregar servicios
            clinica.agregarServicio("Consulta General", 80000);
            clinica.agregarServicio("Odontología", 120000);
            clinica.agregarServicio("Terapia Física", 100000);

            // Obtener paciente Santiago y agendar cita
            Paciente pacienteSanti = clinica.getPacientes().get(0);
            Servicio servicioGeneral = clinica.getServicios().get(0);
            LocalDateTime fechaCitaSanti = LocalDateTime.now().plusDays(2).withHour(10).withMinute(30);
            clinica.agendarCita(pacienteSanti, servicioGeneral, fechaCitaSanti);

            // Obtener paciente Daiana y agendarle también una cita con el mismo servicio
            Paciente pacienteDaiana = clinica.getPacientes().get(1);
            LocalDateTime fechaCitaDaiana = LocalDateTime.now().plusDays(3).withHour(9).withMinute(0);
            clinica.agendarCita(pacienteDaiana, servicioGeneral, fechaCitaDaiana);

            System.out.println(clinica.getPacientes());
            System.out.println(clinica.getServicios());

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        datos();
        FXMLLoader loader = new FXMLLoader(ClinicaApplication.class.getResource("/co/edu/uniquindio/clinica/panel.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent, 800, 600);

        stage.getIcons().add(new Image(Objects.requireNonNull(
                ClinicaApplication.class.getResourceAsStream("/Img/medicamento.png"))));

        stage.setScene(scene);
        stage.setTitle("Clinica");
        stage.setResizable(true);

        stage.show();
    }
}