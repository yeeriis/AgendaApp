package com.mycompany.agendaapp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrincipalController implements Initializable {

    @FXML
    private TableView<usuario> tablaContactos;
    @FXML
    private TableColumn<usuario, String> columnaNombre;
    @FXML
    private TableColumn<usuario, String> columnaApellidos;
    @FXML
    private TableColumn<usuario, String> columnaDireccion;
    @FXML
    private TableColumn<usuario, String> columnaTelefono;
    @FXML
    private TableColumn<usuario, String> columnaEdad;
    @FXML
    private TextField campoBuscar;
    @FXML
    private Button buscarUsuario;

    private ObservableList<usuario> listaContactos;
    private Connection conexion;
    
    private static final String URL = "jdbc:mysql://localhost:3306/agenda";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    private final SecundarioController SecundarioController;
    
    public PrincipalController() {
        SecundarioController = new SecundarioController(); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conectarDB();
            cargarContactos();
        } catch (SQLException e) {}
        
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        
        tablaContactos.setItems(listaContactos);

    }

    private void conectarDB() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/agenda";
        String usuario = "root";
        String contrasena = "";
        conexion = DriverManager.getConnection(url, usuario, contrasena);
    }

    private void cargarContactos() throws SQLException {
        listaContactos = FXCollections.observableArrayList();

        String consulta = "SELECT * FROM usuarios";
        PreparedStatement statement = conexion.prepareStatement(consulta);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {
            String nombre = resultado.getString("nombre");
            String apellidos = resultado.getString("apellidos");
            String direccion = resultado.getString("direccion");
            String telefono = resultado.getString("telefono");
            String edad = resultado.getString("edad");

            usuario usuario = new usuario(nombre, apellidos, direccion, telefono, edad);
            listaContactos.add(usuario);
        }

        statement.close();
        resultado.close();
    }
    
    @FXML
    public void buscarUsuario(ActionEvent event, String nombre) {
        ObservableList<usuario> resultados;
        try {
            resultados = SecundarioController.buscarUsuarioPorNombre(nombre);
            tablaContactos.setItems(resultados);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
//    @FXML
//    private void filtrarContactos(ActionEvent event, String busqueda) {
//        ObservableList<usuario> contactosFiltrados = FXCollections.observableArrayList();
//
//        for (usuario contacto : listaContactos) {
//            if (contacto.getNombre().contains(busqueda.toLowerCase())) {
//                contactosFiltrados.add(contacto);
//            }
//        }
//
//        tablaContactos.setItems(contactosFiltrados);
//    }

    @FXML
    private void abrirRegistrarContacto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registrarUsuario.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Agregar Usuario");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
