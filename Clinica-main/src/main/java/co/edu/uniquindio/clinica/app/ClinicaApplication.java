package co.edu.uniquindio.clinica.app;

import co.edu.uniquindio.clinica.controladores.ControladorPrincipal;
import co.edu.uniquindio.clinica.factory.Suscripcion;
import co.edu.uniquindio.clinica.factory.SuscripcionBasicaFactory;
import co.edu.uniquindio.clinica.factory.SuscripcionFactory;
import co.edu.uniquindio.clinica.factory.SuscripcionPremiumFactory;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.entidades.Servicio;
import co.edu.uniquindio.clinica.servicios.ClinicaServicio;
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
        ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
        try {
            ClinicaServicio clinica = new ClinicaServicio();
            controladorPrincipal.inicializarConClinica(clinica);


            SuscripcionFactory suscripcionFactory = new SuscripcionBasicaFactory();
            SuscripcionFactory suscripcionFactory2 = new SuscripcionPremiumFactory();
            Suscripcion suscripcionPremium = suscripcionFactory2.crearSuscripcion();
            Suscripcion suscripcionBasica = suscripcionFactory.crearSuscripcion();


            controladorPrincipal.getClinica().registrarPaciente("123", "Santiago Torres", "3216549870", "santiago.rodriguezt@uqvirtual.edu.co", suscripcionPremium);
            controladorPrincipal.getClinica().registrarPaciente("124", "Daiana Ramirez", "911694991", "santiago.rodriguezt@uqvirtual.edu.co", suscripcionBasica);


            controladorPrincipal.getClinica().registrarServicio("Consulta General", 80000);
            controladorPrincipal.getClinica().registrarServicio("Odontología", 120000);
            controladorPrincipal.getClinica().registrarServicio("Terapia Física", 100000);


            Paciente pacienteSanti = controladorPrincipal.getClinica().getPacienteServicio().getPacienteRepositorio().getPacientes().get(0);
            Servicio servicioGeneral = controladorPrincipal.getClinica().getServicios().get(0);
            LocalDateTime fechaCitaSanti = LocalDateTime.now().plusDays(2).withHour(10).withMinute(30);
            controladorPrincipal.getClinica().agendarCita(pacienteSanti, servicioGeneral, fechaCitaSanti);


            Paciente pacienteDaiana = controladorPrincipal.getClinica().getPacienteServicio().getPacienteRepositorio().getPacientes().get(1);
            LocalDateTime fechaCitaDaiana = LocalDateTime.now().plusDays(3).withHour(9).withMinute(0);
            controladorPrincipal.getClinica().agendarCita(pacienteDaiana, servicioGeneral, fechaCitaDaiana);

            System.out.println(controladorPrincipal.getClinica().getPacienteServicio().getPacienteRepositorio().getPacientes().get(1));
            System.out.println(controladorPrincipal.getClinica().getServicios());



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