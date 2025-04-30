package co.edu.uniquindio.clinica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class ClinicaApplication extends Application {

    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(ClinicaApplication.class.getResource("/co/edu/uniquindio/clinica/panel.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        stage.getIcons().add(new Image(Objects.requireNonNull(ClinicaApplication.class.getResourceAsStream("/Img/medicamento.png"))));
        stage.setScene(scene);
        stage.setTitle("Clinica");
        stage.setResizable(false);
        stage.show();
    }
}
