/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto2p;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Dario Anchundia Cobo
 */


public class LoginViewController {
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imagendefondo;
    @FXML
    private Label lblheladeria;
    @FXML
    private Label icecream;
    @FXML
    private Label lblusuario;
    @FXML
    private TextField tfusuario;
    @FXML
    private Label lblcontrasena;
    @FXML
    private PasswordField tfcontrasena;
    @FXML
    private Button btnlogin;
    @FXML
    private Label lbl;
    public Usuario usuario=new Usuario("Nombre","Apellido","Genero","User","Contrasena");
    private static final String USUARIOS_PATH="/Proyecto2p/usuarios.txt";     //path
    public void initialize(){
        Image image=new Image(getClass().getResourceAsStream("/Proyecto2p/loginfondo.jpg"));      //path
        imagendefondo.setImage(image);
    }
    @FXML
    private void login() throws IOException{
        String user=tfusuario.getText();
        String pass=tfcontrasena.getText();

        if(validarUsuario(user,pass)){
            lbl.setText("Inicio de sesión exitoso.");
            Stage stage=(Stage)tfusuario.getScene().getWindow();
            stage.close();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Bienvenidos.fxml"));
            Parent root=loader.load();
            BienvenidosController controlador=loader.getController();
            controlador.initialize(usuario);
            Stage segundaVentana=new Stage();
            segundaVentana.setScene(new Scene(root));
            segundaVentana.show();
        }else{
            lbl.setText("Credenciales incorrectas.");
        }
    }
    private boolean validarUsuario(String username,String password){
        try(InputStream is=getClass().getResourceAsStream(USUARIOS_PATH);
            BufferedReader br=new BufferedReader(new InputStreamReader(is))){
            String line;
            while((line=br.readLine())!=null){
                String[] datos=line.split(",");
                if (datos.length==5&&datos[3].equals(username)&&datos[4].equals(password)){                  
                    usuario.setNombre(datos[0]);
                    usuario.setApellido(datos[1]);
                    usuario.setGenero(datos[2]);
                    usuario.setUser(datos[3]);
                    usuario.setContrasena(datos[4]);
                    return true;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }    
}