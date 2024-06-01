module com.mycompany.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.proyectofinal to javafx.fxml;
    exports com.mycompany.proyectofinal;
}
