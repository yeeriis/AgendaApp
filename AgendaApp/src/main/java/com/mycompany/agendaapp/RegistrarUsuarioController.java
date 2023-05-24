package com.mycompany.agendaapp;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistrarUsuarioController implements Initializable {

    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoApellidos;
    @FXML
    private TextField campoDireccion;
    @FXML
    private TextField campoTelefono;
    @FXML
    private TextField campoEdad;
    @FXML
    private Button botonGuardar;

    private Connection conexion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conectarDB();
        } catch (SQLException e) {
            mostrarAlerta("Error", "Error al conectar con la base de datos", AlertType.ERROR);
        }
    }

    private void conectarDB() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/agenda";
        String usuario = "root";
        String contrasena = "";
        conexion = DriverManager.getConnection(url, usuario, contrasena);
    }

    @FXML
    private void guardarContacto() {
        String nombre = campoNombre.getText();
        String apellidos = campoApellidos.getText();
        String direccion = campoDireccion.getText();
        String telefono = campoTelefono.getText();
        String edad = campoEdad.getText();

        String consulta = "INSERT INTO usuarios (nombre, apellidos, direccion, telefono, edad) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombre);
            statement.setString(2, apellidos);
            statement.setString(3, direccion);
            statement.setString(4, telefono);
            statement.setString(5, edad);

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                mostrarAlerta("Éxito", "Contacto guardado correctamente", AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "No se pudo guardar el contacto", AlertType.ERROR);
            }

            statement.close();
        } catch (SQLException e) {
            mostrarAlerta("Error", "Error al guardar el contacto", AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiarCampos() {
        campoNombre.clear();
        campoApellidos.clear();
        campoDireccion.clear();
        campoTelefono.clear();
        campoEdad.clear();
    }
}
