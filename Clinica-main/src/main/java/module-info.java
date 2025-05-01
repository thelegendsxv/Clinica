module co.edu.uniquindio.clinica {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.simplejavamail;
    requires org.simplejavamail.core;
    requires java.xml;


    exports co.edu.uniquindio.clinica.controladores;
    opens co.edu.uniquindio.clinica.controladores to javafx.fxml;
    exports co.edu.uniquindio.clinica.app;
    opens co.edu.uniquindio.clinica.app to javafx.fxml;
}