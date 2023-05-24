module com.mycompany.agendaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.agendaapp to javafx.fxml;
    exports com.mycompany.agendaapp;
}
