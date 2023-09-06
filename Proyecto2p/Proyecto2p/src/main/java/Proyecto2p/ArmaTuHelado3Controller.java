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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
public class ArmaTuHelado3Controller {
    @FXML
    private AnchorPane roothaztuhelado3;
    @FXML
    private ImageView imgvfondo;
    @FXML
    private VBox vb;
    @FXML
    private Label lbltitulo;
    @FXML
    private HBox hbtoppings;
    @FXML
    private VBox vbtoppings1;
    @FXML
    private CheckBox check1;  
    @FXML
    private CheckBox check2;
    @FXML
    private CheckBox check3;
    @FXML
    private CheckBox check4;
    @FXML
    private CheckBox check5;
    @FXML
    private CheckBox check6;
    @FXML
    private VBox vbtoppings2;
    @FXML
    private CheckBox check7;
    @FXML
    private CheckBox check8;
    @FXML
    private CheckBox check9;
    @FXML
    private CheckBox check10;
    @FXML
    private CheckBox check11;
    @FXML
    private CheckBox check12;
    @FXML
    private CheckBox check13;
    @FXML
    private CheckBox check14;
    @FXML
    private CheckBox check15;
    @FXML
    private CheckBox check16;
    @FXML
    private HBox hbfinal;
    @FXML
    private Label lblvalorapagar;
    @FXML
    private Button btncontinuar;
    private double ValorAPagar;
    String TOPPINGS_PATH="/Proyecto2p/toppings.txt";
    PedidoActual p=new PedidoActual(null,null,new ArrayList<>());
    public void initialize(Usuario usuario,double valor,PedidoActual pedido){
        p.base=pedido.base;
        p.sabores=pedido.sabores;
        ValorAPagar=valor;
        lblvalorapagar.setText("Valor a pagar: "+String.format("%.2f", valor));
        Image imagenFondo=new Image("/Proyecto2p/haztuheladofondo.jpg");
        imgvfondo.setImage(imagenFondo);
        ArrayList<Topping> toppings=obtenerToppings();
        for(int i=0;i<toppings.size();i++){
            switch(i){
                case 0:
                    check1.setVisible(true);
                    check1.setDisable(false);
                    check1.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                    
                case 1:
                    check2.setVisible(true);
                    check2.setDisable(false);
                    check2.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 2:
                    check3.setVisible(true);
                    check3.setDisable(false);
                    check3.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 3:
                    check4.setVisible(true);
                    check4.setDisable(false);
                    check4.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 4:
                    check5.setVisible(true);
                    check5.setDisable(false);
                    check5.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 5:
                    check6.setVisible(true);
                    check6.setDisable(false);
                    check6.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 6:
                    check7.setVisible(true);
                    check7.setDisable(false);
                    check7.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 7:
                    check8.setVisible(true);
                    check8.setDisable(false);
                    check8.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 8:
                    check9.setVisible(true);
                    check9.setDisable(false);
                    check9.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 9:
                    check10.setVisible(true);
                    check10.setDisable(false);
                    check10.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 10:
                    check11.setVisible(true);
                    check11.setDisable(false);
                    check11.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 11:
                    check12.setVisible(true);
                    check12.setDisable(false);
                    check12.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 12:
                    check13.setVisible(true);
                    check13.setDisable(false);
                    check13.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 13:
                    check14.setVisible(true);
                    check14.setDisable(false);
                    check14.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 14:
                    check15.setVisible(true);
                    check15.setDisable(false);
                    check15.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                case 15:
                    check16.setVisible(true);
                    check16.setDisable(false);
                    check16.setText(toppings.get(i).topping+" - "+toppings.get(i).precio);
                    break;
                default:
                    break;

            }
        }
        check1.setOnAction(e->{
            if(check1.isSelected()){
                ValorAPagar+=Double.parseDouble(check1.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check1.getText().split(" - ")[0],check1.getText().split(" - ")[1]));
            }else{
                ValorAPagar-=Double.parseDouble(check1.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check1.getText().split(" - ")[0],check1.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check2.setOnAction(e->{
            if(check2.isSelected()){
                ValorAPagar+=Double.parseDouble(check2.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check2.getText().split(" - ")[0],check2.getText().split(" - ")[1]));                
            }else{
                ValorAPagar-=Double.parseDouble(check2.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check2.getText().split(" - ")[0],check2.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check3.setOnAction(e->{
            if(check3.isSelected()){
                ValorAPagar+=Double.parseDouble(check3.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check3.getText().split(" - ")[0],check3.getText().split(" - ")[1])); 
            }else{
                ValorAPagar-=Double.parseDouble(check3.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check3.getText().split(" - ")[0],check3.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check4.setOnAction(e->{
            if(check4.isSelected()){
                ValorAPagar+=Double.parseDouble(check4.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check4.getText().split(" - ")[0],check4.getText().split(" - ")[1]));                
            }else{
                ValorAPagar-=Double.parseDouble(check4.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check4.getText().split(" - ")[0],check4.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check5.setOnAction(e->{
            if(check5.isSelected()){
                ValorAPagar+=Double.parseDouble(check5.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check5.getText().split(" - ")[0],check5.getText().split(" - ")[1]));
            }else{
                ValorAPagar-=Double.parseDouble(check5.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check5.getText().split(" - ")[0],check5.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check6.setOnAction(e->{
            if(check6.isSelected()){
                ValorAPagar+=Double.parseDouble(check6.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check6.getText().split(" - ")[0],check6.getText().split(" - ")[1]));
            }else{
                ValorAPagar-=Double.parseDouble(check6.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check6.getText().split(" - ")[0],check6.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check7.setOnAction(e->{
            if(check7.isSelected()){
                ValorAPagar+=Double.parseDouble(check7.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check7.getText().split(" - ")[0],check7.getText().split(" - ")[1]));
            }else{
                ValorAPagar-=Double.parseDouble(check7.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check7.getText().split(" - ")[0],check7.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check8.setOnAction(e->{
            if(check8.isSelected()){
                ValorAPagar+=Double.parseDouble(check8.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check8.getText().split(" - ")[0],check8.getText().split(" - ")[1]));
            }else{
                ValorAPagar-=Double.parseDouble(check8.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check8.getText().split(" - ")[0],check8.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check9.setOnAction(e->{
            if(check9.isSelected()){
                ValorAPagar+=Double.parseDouble(check9.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check9.getText().split(" - ")[0],check9.getText().split(" - ")[1]));
            }else{
                ValorAPagar-=Double.parseDouble(check9.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check9.getText().split(" - ")[0],check9.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check10.setOnAction(e->{
            if(check10.isSelected()){
                ValorAPagar+=Double.parseDouble(check10.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check10.getText().split(" - ")[0],check10.getText().split(" - ")[1]));
            }else{
                ValorAPagar-=Double.parseDouble(check10.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check10.getText().split(" - ")[0],check10.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check11.setOnAction(e->{
            if(check11.isSelected()){
                ValorAPagar+=Double.parseDouble(check11.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check11.getText().split(" - ")[0],check11.getText().split(" - ")[1]));
            }else{
                ValorAPagar-=Double.parseDouble(check11.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check11.getText().split(" - ")[0],check11.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check12.setOnAction(e->{
            if(check12.isSelected()){
                ValorAPagar+=Double.parseDouble(check12.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check12.getText().split(" - ")[0],check12.getText().split(" - ")[1]));
            }else{
                ValorAPagar-=Double.parseDouble(check12.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check12.getText().split(" - ")[0],check12.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check13.setOnAction(e->{
            if(check13.isSelected()){
                ValorAPagar+=Double.parseDouble(check13.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check13.getText().split(" - ")[0],check13.getText().split(" - ")[1]));
            }else{
                ValorAPagar-=Double.parseDouble(check13.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check13.getText().split(" - ")[0],check13.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check14.setOnAction(e->{
            if(check14.isSelected()){
                ValorAPagar+=Double.parseDouble(check14.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check14.getText().split(" - ")[0],check14.getText().split(" - ")[1]));
            }else{
                ValorAPagar-=Double.parseDouble(check14.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check14.getText().split(" - ")[0],check14.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check15.setOnAction(e->{
            if(check15.isSelected()){
                ValorAPagar+=Double.parseDouble(check15.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check15.getText().split(" - ")[0],check15.getText().split(" - ")[1]));
            }else{
                ValorAPagar-=Double.parseDouble(check15.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check15.getText().split(" - ")[0],check15.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        check16.setOnAction(e->{
            if(check16.isSelected()){
                ValorAPagar+=Double.parseDouble(check16.getText().split(" - ")[1]);
                p.toppings.add(new Topping(check16.getText().split(" - ")[0],check16.getText().split(" - ")[1]));
            }else{
                ValorAPagar-=Double.parseDouble(check16.getText().split(" - ")[1]);
                p.toppings.remove(new Topping(check16.getText().split(" - ")[0],check16.getText().split(" - ")[1]));
            }
            lblvalorapagar.setText("Valor a pagar: "+ValorAPagar);
        });
        btncontinuar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Stage stage =(Stage)btncontinuar.getScene().getWindow();
                stage.close();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("ArmaTuHelado4.fxml"));
                Parent root4=null;
                try{
                    root4 = loader.load();
                }catch(IOException ex){
                ex.printStackTrace();
                }
                ArmaTuHelado4Controller controlador=loader.getController();
                controlador.initialize(usuario,ValorAPagar,p,false);
                Stage ventana4=new Stage();
                ventana4.setTitle("ArmaTuHelado4.fxml");
                ventana4.setScene(new Scene(root4));
                ventana4.show();
            }
        });
}
    public ArrayList<Topping> obtenerToppings(){
        ArrayList<Topping> toppings=new ArrayList<>();
        try(InputStream is=getClass().getResourceAsStream(TOPPINGS_PATH);
            BufferedReader br=new BufferedReader(new InputStreamReader(is))){
            String line;
            while((line=br.readLine())!=null){
                String[] datos=line.split(",");
                if(datos.length==2){
                    toppings.add(new Topping(datos[0],datos[1]));
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return toppings;
    }
}