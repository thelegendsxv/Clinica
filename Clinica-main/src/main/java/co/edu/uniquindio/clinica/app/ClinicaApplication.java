package co.edu.uniquindio.clinica.app;

import co.edu.uniquindio.clinica.controladores.ControladorPrincipal;
import co.edu.uniquindio.clinica.modelo.enumer.TipoSuscripcion;
import co.edu.uniquindio.clinica.suscripcion.Suscripcion;
import co.edu.uniquindio.clinica.suscripcionfactory.SuscripcionBasicaFactory;
import co.edu.uniquindio.clinica.suscripcionfactory.SuscripcionFactory;
import co.edu.uniquindio.clinica.suscripcionfactory.SuscripcionPremiumFactory;
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

//App
public class ClinicaApplication extends Application {
    public void datos() {
        ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
        try {
            ClinicaServicio clinica = new ClinicaServicio();
            controladorPrincipal.inicializarConClinica(clinica);


            controladorPrincipal.getClinica().registrarPaciente("123", "Santiago Torres", "3216549870", "santiago.rodriguezt@uqvirtual.edu.co", TipoSuscripcion.BASICA);
            controladorPrincipal.getClinica().registrarPaciente("124", "Daiana Ramirez", "911694991", "amore@gmail.com", TipoSuscripcion.PREMIUM);



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