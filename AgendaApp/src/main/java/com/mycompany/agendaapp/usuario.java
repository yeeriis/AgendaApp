/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agendaapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gerard
 */
public class usuario {
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String edad;
    
    public usuario(String nombre, String apellidos, String direccion, String telefono, String edad){
        this.nombre = new String(nombre);
        this.apellidos = new String(apellidos);
        this.direccion = new String(direccion);
        this.telefono = new String(telefono);
        this.edad = new String(edad);
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getEdad() {
        return edad;
    }
    
    public void setEdad(String edad) {
        this.edad = edad;
    }

    
    
}
