/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto2p;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 *
 * @author Dario Anchundia Cobo
 */
public class BienvenidosController {
    @FXML
    private ImageView imagenfondo;
    @FXML
    private AnchorPane root;
    @FXML
    private Label lblwelcome;
    @FXML
    private Button btnlocation;
    @FXML
    private Button btnhacerpedido;
    @FXML
    private ListView<String> lvpedidos;
    @FXML
    private VBox vbpedidos;
    private static final String USUARIOS_PATH="/Proyecto2p/usuarios.txt";
    private static final String PEDIDOS_PATH="/Proyecto2p/pedidos.txt";
    public void initialize(Usuario usuario){
        Image image=new Image(getClass().getResourceAsStream("/Proyecto2p/bienvenidosfondo.jpg"));
        imagenfondo.setImage(image);
        switch(usuario.getGenero()){
            case "M":
                lblwelcome.setFont(Font.font("System",FontWeight.BOLD,24));
                lblwelcome.setText("Bienvenido, "+usuario.getNombre());
                lblwelcome.setTextFill(Color.BROWN);
                break;
            case "F":
                lblwelcome.setFont(Font.font("System",FontWeight.BOLD,24));
                lblwelcome.setText("Bienvenida, "+usuario.getNombre());
                lblwelcome.setTextFill(Color.BROWN);
                break;
        }
        StringBuilder pedidos=new StringBuilder();
        try(InputStream is=getClass().getResourceAsStream("/Proyecto2p/pedidos.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(is))){
            String line;
            while((line=br.readLine())!=null){
                pedidos.append(line).append("\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        lvpedidos.getItems().addAll(pedidos.toString().split("\n"));
        mostrarPedidos();        
    }
    @FXML
    private void EncuentraNuestrosLocales(){
    }
    @FXML
    private void HazTuPedido() {
    }
    private void mostrarPedidos(){
        Stage stage_pedidos=new Stage();
        stage_pedidos.setTitle("Lista de Pedidos");
        VBox vbpedidos=new VBox();
        vbpedidos.getChildren().add(lvpedidos);
        stage_pedidos.setScene(new Scene(vbpedidos, 200, 200));
        stage_pedidos.initModality(Modality.APPLICATION_MODAL);
        stage_pedidos.show();
    }
}