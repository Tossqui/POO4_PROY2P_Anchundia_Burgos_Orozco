/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto2p;

/**
 *
 * @author Thomas Burgos
 */

public class Usuario {
    private String Nombre;
    private String Genero;
    private String User;
    private String Contrasena;

    public Usuario(String Nombre, String Genero, String User, String Contrasena) {
        this.Nombre = Nombre;
        this.Genero = Genero;
        this.User = User;
        this.Contrasena = Contrasena;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getNombre() {
        return Nombre;
    }
    public String getGenero() {
        return Genero;
    }
    public String getUser() {
        return User;
    }
    public String getContrasena() {
        return Contrasena;
    }  

    @Override
    public String toString() {
        return "Usuario{" + "Nombre=" + Nombre + ", Genero=" + Genero + ", User=" + User + ", Contrasena=" + Contrasena + '}';
    }
    
}
