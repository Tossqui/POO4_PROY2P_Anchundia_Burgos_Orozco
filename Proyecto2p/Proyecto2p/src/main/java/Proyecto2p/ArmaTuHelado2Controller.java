/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Dario Anchundia Cobo
 */
public class ArmaTuHelado2Controller {
    @FXML
    Label lblex;
    @FXML
    private AnchorPane rootpaso2;
    @FXML
    private ImageView imgvfondo;
    @FXML
    private Label lbltitulo;
    @FXML
    private VBox vb;
    @FXML
    private HBox hbopciones;
    @FXML
    private VBox vbsabor1;
    @FXML
    private Label lblsabor1;
    @FXML
    private ComboBox cbsabor1;
    @FXML
    private VBox vbsabor2;
    @FXML
    private Label lblsabor2;
    @FXML
    private ComboBox cbsabor2;
    @FXML
    private HBox hb;
    @FXML
    private Label valorAPagarLabel;
    @FXML
    private Button btnContinuar;                
    String SABORES_PATH="/Proyecto2p/sabores.txt";
    double ValorAPagar=0;
    ArrayList<Sabor> sabores=obtenerSabores();
    PedidoActual p=new PedidoActual(null,new ArrayList<>(),new ArrayList<>());;
    public void initialize(Usuario usuario,double valor, PedidoActual pedido){
        p.base=new Base(pedido.base.base.toString(),pedido.base.precio.toString());
        valorAPagarLabel.setText("Valor a pagar: "+String.format("%.2f", valor));
        ValorAPagar+=valor;
        Image imagenFondo=new Image("/Proyecto2p/haztuheladofondo.jpg");
        imgvfondo.setImage(imagenFondo);
        ArrayList<String> nombreSabores=new ArrayList<>();
        for(Sabor s:sabores){
            nombreSabores.add(s.sabor);
        }
        for(int i=0;i<nombreSabores.size();i++){
            for(int j=0;j<sabores.size();j++){
                if(nombreSabores.get(i).equals(sabores.get(j).sabor)){
                    cbsabor1.getItems().add(nombreSabores.get(i)+" - "+sabores.get(j).precio);
                    cbsabor2.getItems().add(nombreSabores.get(i)+" - "+sabores.get(j).precio);
                }
            }
        }       
        Collections.sort(nombreSabores);
        cbsabor1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(cbsabor2.getSelectionModel().getSelectedItem()==null|cbsabor1.getSelectionModel().getSelectedItem().equals(cbsabor2.getSelectionModel().getSelectedItem())){
                    String s=(String)cbsabor1.getSelectionModel().getSelectedItem();
                    double valorcb1=Double.parseDouble(s.split(" - ")[1]);
                    valorAPagarLabel.setText("Valor a pagar: "+String.format("%.2f",(valorcb1+ValorAPagar)));
                }else{
                    String s=(String)cbsabor1.getSelectionModel().getSelectedItem();
                    double valorcb1=Double.parseDouble(s.split(" - ")[1]);
                    String s2=(String)cbsabor2.getSelectionModel().getSelectedItem();
                    valorAPagarLabel.setText("Valor a pagar: "+String.format("%.2f",(valorcb1+ValorAPagar+Double.parseDouble(s2.split(" - ")[1]))));
                }
            }
        });
        cbsabor2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(cbsabor1.getSelectionModel().getSelectedItem()==null|cbsabor2.getSelectionModel().getSelectedItem().equals(cbsabor1.getSelectionModel().getSelectedItem())){
                    String s2=(String)cbsabor2.getSelectionModel().getSelectedItem();
                    double valorcb2=Double.parseDouble(s2.split(" - ")[1]);
                    valorAPagarLabel.setText("Valor a pagar: "+String.format("%.2f",(valorcb2+ValorAPagar)));
                }else{
                    String s2=(String)cbsabor1.getSelectionModel().getSelectedItem();
                    double valorcb1=Double.parseDouble(s2.split(" - ")[1]);
                    String s1=(String)cbsabor2.getSelectionModel().getSelectedItem();
                    valorAPagarLabel.setText("Valor a pagar: "+String.format("%.2f",(valorcb1+ValorAPagar+Double.parseDouble(s1.split(" - ")[1]))));
                }
            }
        });
        btnContinuar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(cbsabor1.getSelectionModel().getSelectedItem()!=null&&!cbsabor1.getSelectionModel().getSelectedItem().equals(cbsabor2.getSelectionModel().getSelectedItem())){
                    String valor1=(String)cbsabor1.getSelectionModel().getSelectedItem();
                    ValorAPagar+=Double.parseDouble(valor1.split(" - ")[1]);
                    p.sabores.add(new Sabor(valor1.split(" - ")[0],valor1.split(" - ")[1]));
                }if(cbsabor2.getSelectionModel().getSelectedItem()!=null&&!cbsabor2.getSelectionModel().getSelectedItem().equals(cbsabor1.getSelectionModel().getSelectedItem())){
                    String valor2=(String)cbsabor2.getSelectionModel().getSelectedItem();
                    ValorAPagar+=Double.parseDouble(valor2.split(" - ")[1]);
                    p.sabores.add(new Sabor(valor2.split(" - ")[0],valor2.split(" - ")[1]));
                }else if(cbsabor1.getSelectionModel().getSelectedItem()==null&&cbsabor2.getSelectionModel().getSelectedItem()==null){
                    Stage s=(Stage)btnContinuar.getScene().getWindow();
                    lblex.setVisible(true);
                    throw new IncompleteStageException("Debes elegir al menos un sabor para continuar.", s);
                }else if(cbsabor1.getSelectionModel().getSelectedItem().equals(cbsabor2.getSelectionModel().getSelectedItem())){
                    String valor2=(String)cbsabor2.getSelectionModel().getSelectedItem();
                    ValorAPagar+=Double.parseDouble(valor2.split(" - ")[1]);
                    p.sabores.add(new Sabor(valor2.split(" - ")[0],valor2.split(" - ")[1]));
                }
                if(ValorAPagar!=valor){
                    Stage stage=(Stage)rootpaso2.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("ArmaTuHelado3.fxml"));
                    Parent nroot=null;
                    try {
                        nroot = loader.load();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    ArmaTuHelado3Controller controlador=loader.getController();
                    controlador.initialize(usuario,ValorAPagar,p);
                    Stage ventana3=new Stage();
                    ventana3.setTitle("ArmaTuHelado3.fxml");
                    ventana3.setScene(new Scene(nroot));
                    ventana3.show();
                }
            }
        });
    }
    public ArrayList<Sabor> obtenerSabores(){
        ArrayList<Sabor> sabores=new ArrayList<>();
        try(InputStream is=getClass().getResourceAsStream(SABORES_PATH);
            BufferedReader br=new BufferedReader(new InputStreamReader(is))){
            String line;
            while((line=br.readLine())!=null){
                String[] datos=line.split(",");
                if(datos.length==2){
                    sabores.add(new Sabor(datos[0],datos[1]));
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return sabores;
    }
}
