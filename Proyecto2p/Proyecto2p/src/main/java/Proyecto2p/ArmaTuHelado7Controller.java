/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto2p;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Dario Anchundia Cobo
 */
public class ArmaTuHelado7Controller {
    @FXML
    AnchorPane rootfinal=new AnchorPane();
    @FXML
    ImageView imgvfondo=new ImageView();
    @FXML
    VBox vb=new VBox();
    @FXML
    Label lbltitulo=new Label();
    @FXML
    Label lblsubtitulo=new Label();
    @FXML
    ImageView imgvimage=new ImageView();
    @FXML
    Label lblfinal=new Label();    
    
    public void initialize(Pedido p,Stage stage){
        Image image=new Image(getClass().getResourceAsStream("/Proyecto2p/haztuheladofondo.jpg"));      
        Image image2=new Image(getClass().getResourceAsStream("/Proyecto2p/maquina.GIF"));
        imgvfondo.setImage(image);
        imgvimage.setImage(image2);
        lblsubtitulo.setText("Tu pedido es el #"+p.getIdPedido()+". Te llamaremos cuando esté listo.");
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> stage.close());        
        delay.play();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            int valor = Integer.parseInt(lblfinal.getText().split(" ")[5]);
            lblfinal.setText("Esta ventana se cerrará en "+String.valueOf(valor-1)+" segundos...");
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
