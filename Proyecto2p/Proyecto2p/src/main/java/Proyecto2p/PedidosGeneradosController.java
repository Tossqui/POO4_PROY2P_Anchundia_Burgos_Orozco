/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto2p;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Dario Anchundia Cobo
 */
public class PedidosGeneradosController {
    @FXML
    private AnchorPane rootpedidos;
    @FXML
    private ListView<String> lvpedidos;
    private ArrayList<String> items = new ArrayList<>();
    private Stage stage;
    private static final String PEDIDOS_PATH="/Proyecto2p/pedidos.txt";
    public void initialize(){         
        actualizarListView();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            actualizarListView();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play(); 
        
    }
    public void actualizarListView(){
        ArrayList<String> listapedidos = obtenerPedidos();
        lvpedidos.getItems().clear();
        lvpedidos.getItems().addAll(listapedidos);
    }
    public ArrayList<String> obtenerPedidos() {
        ArrayList<String> listapedidos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Proyecto2p/pedidos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listapedidos.add(line.split(",")[1]+","+line.split(",")[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listapedidos;
    }
}
