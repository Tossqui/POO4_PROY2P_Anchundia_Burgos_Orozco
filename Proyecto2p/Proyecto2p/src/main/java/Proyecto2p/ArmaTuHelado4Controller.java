/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto2p;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Dario Anchundia Cobo
 */
public class ArmaTuHelado4Controller implements Pagable{
    @FXML
    private AnchorPane roothaztuhelado;
    @FXML
    private ImageView imgvfondo;
    @FXML
    private VBox vb;
    @FXML
    private Label lbltitulo;
    @FXML
    private HBox hbpedido;
    @FXML
    public ListView lvpedido;
    @FXML
    private Button btneliminar;
    @FXML
    private HBox hbfinal;
    @FXML
    private Label lblvalorapagar;
    @FXML
    private Button btnconfirmar;
    @FXML
    private Button btncancelar;
    @FXML
    PedidoActual p=new PedidoActual(null,new ArrayList<>(),new ArrayList<>());
    private static final String PEDIDOS_PATH="/Proyecto2p/pedidos.txt";
    private static final String PAGOS_PATH="/Proyecto2p/pedidos.txt";
    public static Label cerrar=new Label("");
    double ValorAPagar;
    public void initialize(Usuario usuario,double valor,PedidoActual pedido,Boolean b){
        p.base=pedido.base;
        p.sabores=pedido.sabores;
        p.toppings=pedido.toppings;
        ValorAPagar=valor;
        lvpedido.setStyle("-fx-control-inner-background: #FFFFFF;");
        lblvalorapagar.setText("Valor a pagar: "+String.format("%.2f", ValorAPagar));
        Image imagenFondo=new Image("/Proyecto2p/haztuheladofondo.jpg");
        imgvfondo.setImage(imagenFondo);
        lvpedido.getItems().add("Base: "+pedido.base.base);
        
        for(Sabor s:pedido.sabores){
            lvpedido.getItems().add("Sabor: "+s.sabor);
        }
        for(Topping t:pedido.toppings){
            lvpedido.getItems().add("Topping:"+t.topping);
        }
        lvpedido.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvpedido.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null&&newValue.toString().split(":")[0].equals("Sabor")&&p.sabores.size()!=1){
                btneliminar.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        if(p.sabores.size()!=1){
                            Stage stage = new Stage();
                            AnchorPane rootmensaje=new AnchorPane();
                            VBox vbh=new VBox();
                            vbh.setPrefSize(450.0, 300);
                            vbh.setSpacing(60);
                            vbh.setPadding(new Insets(100, 20, 0, 20));
                            Label lbl=new Label();
                            lbl.setText("¿Está seguro de eliminar el componente?");
                            lbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                            HBox hb=new HBox();
                            hb.setPrefSize(150,200);
                            hb.setSpacing(150);
                            Button btnaceptar=new Button();
                            btnaceptar.setPrefSize(100, 40);
                            btnaceptar.setStyle("-fx-background-color: linear-gradient(to bottom, #ffde59, #ff914d);");
                            btnaceptar.setText("Aceptar");
                            btnaceptar.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                            Button btncancelarm=new Button();
                            btncancelarm.setPrefSize(100, 40);
                            btncancelarm.setStyle("-fx-background-color: linear-gradient(to bottom, #ffde59, #ff914d);");
                            btncancelarm.setText("Cancelar");
                            btncancelarm.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                            rootmensaje.setStyle("-fx-background-color: #E9DAFF;");
                            Scene scene = new Scene(rootmensaje,450,300);
                            rootmensaje.getChildren().add(vbh);
                            vbh.getChildren().add(lbl);
                            vbh.getChildren().add(hb);
                            hb.getChildren().add(btnaceptar);
                            hb.getChildren().add(btncancelarm);
                            vbh.setMargin(hb,new Insets(0,50,0,50));
                            stage.setScene(scene);
                            stage.show();
                            btnaceptar.setOnAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent event){
                                    stage.close();
                                    for(Sabor sabor:p.sabores){
                                        if(sabor.sabor.equals(newValue.toString().split(": ")[1])){
                                            lvpedido.getItems().remove(newValue);
                                            p.sabores.remove(sabor);
                                            ValorAPagar-=Double.parseDouble(sabor.precio);
                                            lblvalorapagar.setText("Valor a pagar: "+String.format("%.2f", ValorAPagar));
                                        } 
                                    }                  
                                }
                            });
                            btncancelarm.setOnAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent event){
                                    stage.close();
                                }
                            });
                        }else{
                            Stage s=(Stage)lvpedido.getScene().getWindow();
                            throw new IncompleteStageException("Debe quedar al menos 1 sabor.",s);
                        }
                        
                    }
                });            
            }else if(!newValue.toString().equals("Sabor")){
                btneliminar.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        Stage s=(Stage)lvpedido.getScene().getWindow();
                        throw new IncompleteStageException("Debes elegir un sabor.",s);                     
                    }
                });
            }else if(p.sabores.size()==1){
                btneliminar.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        Stage s=(Stage)lvpedido.getScene().getWindow();
                        throw new IncompleteStageException("Debe quedar al menos 1 sabor.",s);
                    }
                });
            }
        });
        btncancelar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Stage stage = new Stage();
                AnchorPane rootmensaje=new AnchorPane();
                VBox vbh=new VBox();
                vbh.setPrefSize(450.0, 300);
                vbh.setSpacing(60);
                vbh.setPadding(new Insets(100, 20, 0, 20));
                Label lbl=new Label();
                lbl.setText("¿Está seguro de cancelar su compa?");
                lbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                HBox hb=new HBox();
                hb.setPrefSize(150,200);
                hb.setSpacing(150);
                Button btnaceptar=new Button();
                btnaceptar.setPrefSize(100, 40);
                btnaceptar.setStyle("-fx-background-color: linear-gradient(to bottom, #ffde59, #ff914d);");
                btnaceptar.setText("Aceptar");
                btnaceptar.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                Button btncancelarm=new Button();
                btncancelarm.setPrefSize(100, 40);
                btncancelarm.setStyle("-fx-background-color: linear-gradient(to bottom, #ffde59, #ff914d);");
                btncancelarm.setText("Cancelar");
                btncancelarm.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                rootmensaje.setStyle("-fx-background-color: #E9DAFF;");
                Scene scene = new Scene(rootmensaje,450,300);
                rootmensaje.getChildren().add(vbh);
                vbh.getChildren().add(lbl);
                vbh.getChildren().add(hb);
                hb.getChildren().add(btnaceptar);
                hb.getChildren().add(btncancelarm);
                vbh.setMargin(hb,new Insets(0,50,0,50));
                stage.setScene(scene);
                stage.show();
                btnaceptar.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        stage.close();
                        Stage s=(Stage)btncancelar.getScene().getWindow();
                        s.close();
                    }
                });
                btncancelarm.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        stage.close();
                    }
                });
                         
            }       
        });
        btnconfirmar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Locale.setDefault(Locale.US);
                Pedido pedidoaguardar=new Pedido(null,null,0);
                pedidoaguardar.setNombreCliente(usuario.getNombre());
                try{
                    File file=new File("src/main/resources/Proyecto2p/pedidos.txt");
                    FileWriter fileWriter = new FileWriter(file,true);
                    ArrayList<String> ListaPedidos=getListaPedidos();
                    try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                        switch (Integer.toString(ListaPedidos.size()).length()) {
                            case 0:
                                bufferedWriter.write("0000"+","+usuario.getNombre()+","+String.format("%.2f", ValorAPagar)+"\n");
                                pedidoaguardar.setIdPedido("0000");
                                pedidoaguardar.setValor(ValorAPagar);
                                break;
                            case 1:
                                bufferedWriter.write("000"+Integer.toString(ListaPedidos.size())+","+usuario.getNombre()+","+String.format("%.2f", ValorAPagar)+"\n");
                                pedidoaguardar.setIdPedido("000"+Integer.toString(ListaPedidos.size()));
                                pedidoaguardar.setValor(ValorAPagar);
                                break;
                            case 2:
                                bufferedWriter.write("00"+Integer.toString(ListaPedidos.size())+","+usuario.getNombre()+","+String.format("%.2f", ValorAPagar)+"\n");
                                pedidoaguardar.setIdPedido("00"+Integer.toString(ListaPedidos.size()));
                                pedidoaguardar.setValor(ValorAPagar);
                                break;
                            case 3:
                                bufferedWriter.write("0"+Integer.toString(ListaPedidos.size())+","+usuario.getNombre()+","+String.format("%.2f", ValorAPagar)+"\n");
                                pedidoaguardar.setIdPedido("0"+Integer.toString(ListaPedidos.size()));
                                pedidoaguardar.setValor(ValorAPagar);
                                break;
                            case 4:
                                bufferedWriter.write(Integer.toString(ListaPedidos.size())+","+usuario.getNombre()+","+String.format("%.2f", ValorAPagar)+"\n");
                                pedidoaguardar.setIdPedido("0"+Integer.toString(ListaPedidos.size()));
                                pedidoaguardar.setValor(ValorAPagar);
                                break;
                            default:
                                break;
                        }
                        bufferedWriter.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
                File archivo = new File("src/main/resources/Proyecto2p/pedido"+pedidoaguardar.getIdPedido()+".bin");
                try{
                    archivo.createNewFile();
                }catch (Exception e){
                    e.printStackTrace();
                }
                try(FileOutputStream fileOut = new FileOutputStream(archivo);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)){
                    objectOut.writeObject(pedidoaguardar);
                }catch (Exception e){
                    e.printStackTrace();
                }
                Stage s=(Stage)btnconfirmar.getScene().getWindow();
                s.close();
                Stage stagep = new Stage();
                AnchorPane rootp=new AnchorPane();
                Scene scenep=new Scene(rootp,600,650);
                rootp.setStyle("-fx-background-color: #E9DAFF;");
                ImageView imgvfondop=new ImageView();
                imgvfondop.setFitHeight(400);
                imgvfondop.setFitWidth(600);
                imgvfondop.setImage(imagenFondo);
                VBox vbcont=new VBox();
                vbcont.setPrefSize(600,650);
                vbcont.setPadding(new Insets(40,30,0,30));
                vbcont.setSpacing(10);
                Label lbltitulo=new Label("Pago");
                lbltitulo.setPrefSize(520,90);
                lbltitulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                lbltitulo.setStyle("-fx-text-fill: #7300ff;");
                lbltitulo.setAlignment(Pos.BOTTOM_LEFT);
                HBox hbops=new HBox();
                hbops.setPrefSize(540,25);
                hbops.setSpacing(100);
                RadioButton rbe=new RadioButton();
                ToggleGroup tg=new ToggleGroup();
                rbe.setText("Efectivo");
                rbe.setToggleGroup(tg);
                RadioButton rbt=new RadioButton();
                rbt.setText("Tarjeta");
                rbt.setToggleGroup(tg);
                Label lbldetalle=new Label("Detalle de pago");
                lbldetalle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                HBox hbdetalles=new HBox();
                hbdetalles.setSpacing(35);
                hbdetalles.setPrefSize(200,100);
                VBox vblabels1=new VBox();
                vblabels1.setPrefSize(100,200);
                vblabels1.setSpacing(5);
                Label lblvalor=new Label("Valor a pagar:");
                lblvalor.setPrefHeight(25);
                Label lbladicional=new Label("Adicional Tarjeta:");
                lbladicional.setPrefHeight(25);
                Label lbliva=new Label("IVA:");
                lbliva.setPrefHeight(25);
                Label lbltotal=new Label("Total a pagar:");
                VBox vbtext1=new VBox();
                vbtext1.setPrefSize(200,200);
                vbtext1.setSpacing(5);
                TextField tfvalor=new TextField();
                TextField tfadicional=new TextField();
                TextField tfiva=new TextField();
                TextField tftotal=new TextField();
                HBox hbbtns=new HBox();
                hbbtns.setPadding(new Insets(220,0,0,0));
                hbbtns.setSpacing(15);
                Label lblex=new Label("No ha llenado todos los campos necesarios para continuar con su compra.");
                lblex.setPrefSize(340,40);
                lblex.setWrapText(true);
                lblex.setVisible(false);
                Button btnconf=new Button();
                Button btncancel=new Button();
                btnconf.setText("Confirmar");
                btnconf.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                btnconf.setStyle("-fx-background-color: linear-gradient(to bottom, #ffde59, #ff914d);");
                btncancel.setText("Cancelar");
                btncancel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                btncancel.setStyle("-fx-background-color: linear-gradient(to bottom, #ffde59, #ff914d);");
                rootp.getChildren().add(imgvfondo);
                rootp.getChildren().add(vbcont);
                vbcont.getChildren().add(lbltitulo);
                vbcont.getChildren().add(hbops);
                vbcont.getChildren().add(lbldetalle);
                vbcont.getChildren().add(hbdetalles);
                vbcont.getChildren().add(hbbtns);
                hbops.getChildren().add(rbe);
                hbops.getChildren().add(rbt);
                hbdetalles.getChildren().add(vblabels1);
                hbdetalles.getChildren().add(vbtext1);
                hbbtns.getChildren().add(lblex);
                hbbtns.getChildren().add(btnconf);
                hbbtns.getChildren().add(btncancel);
                vblabels1.getChildren().add(lblvalor);
                vblabels1.getChildren().add(lbladicional);
                vblabels1.getChildren().add(lbliva);
                vblabels1.getChildren().add(lbltotal);
                vbtext1.getChildren().add(tfvalor);
                vbtext1.getChildren().add(tfadicional);
                vbtext1.getChildren().add(tfiva);
                vbtext1.getChildren().add(tftotal);
                stagep.setScene(scenep);
                stagep.show();
                btnconf.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        if(!rbe.isSelected()&&!rbt.isSelected()){
                            lblex.setVisible(true);
                            throw new IncompleteStageException("No ha llenado todos los campos necesarios para continuar con su compra.", stagep);
                        }else{
                            stagep.close();
                        }
                    }
                });
                btncancel.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        Stage stage = new Stage();
                        AnchorPane rootmensaje=new AnchorPane();
                        VBox vbh=new VBox();
                        vbh.setPrefSize(450.0, 300);
                        vbh.setSpacing(60);
                        vbh.setPadding(new Insets(100, 20, 0, 20));
                        Label lbl=new Label();
                        lbl.setText("¿Está seguro de cancelar su compa?");
                        lbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                        HBox hb=new HBox();
                        hb.setPrefSize(150,200);
                        hb.setSpacing(150);
                        Button btnaceptar=new Button();
                        btnaceptar.setPrefSize(100, 40);
                        btnaceptar.setStyle("-fx-background-color: linear-gradient(to bottom, #ffde59, #ff914d);");
                        btnaceptar.setText("Aceptar");
                        btnaceptar.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                        Button btncancelarm=new Button();
                        btncancelarm.setPrefSize(100, 40);
                        btncancelarm.setStyle("-fx-background-color: linear-gradient(to bottom, #ffde59, #ff914d);");
                        btncancelarm.setText("Cancelar");
                        btncancelarm.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                        rootmensaje.setStyle("-fx-background-color: #E9DAFF;");
                        Scene scene = new Scene(rootmensaje,450,300);
                        rootmensaje.getChildren().add(vbh);
                        vbh.getChildren().add(lbl);
                        vbh.getChildren().add(hb);
                        hb.getChildren().add(btnaceptar);
                        hb.getChildren().add(btncancelarm);
                        vbh.setMargin(hb,new Insets(0,50,0,50));
                        stage.setScene(scene);
                        stage.show();
                        btnaceptar.setOnAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event){
                                stage.close();
                                stagep.close();
                            }
                        });
                        btncancelarm.setOnAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event){
                                stage.close();
                            }
                        });
                    }
                });
                rbe.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        lblex.setVisible(false);
                        vbcont.getChildren().clear();
                        vbcont.getChildren().add(lbltitulo);
                        vbcont.getChildren().add(hbops);
                        vbcont.getChildren().add(lbldetalle);
                        vbcont.getChildren().add(hbdetalles);
                        Locale.setDefault(Locale.US);
                        tfvalor.setText(String.format("%.2f", ValorAPagar));
                        tfvalor.setEditable(false);
                        tfadicional.setText("0.00");
                        tfadicional.setEditable(false);
                        tfiva.setText(String.format("%.2f", ValorAPagar*0.12));
                        tfiva.setEditable(false);
                        tftotal.setText(String.format("%.2f", ValorAPagar*1.12));
                        tftotal.setEditable(false);
                        vbcont.getChildren().remove(hbbtns);
                        Label lbl=new Label("Acércate a caja para pagar tu pedido.");
                        lbl.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                        lbl.setPrefSize(540,112);
                        lbl.setAlignment(Pos.BOTTOM_CENTER);
                        hbbtns.setPadding(new Insets(115,0,0,0));
                        vbcont.getChildren().add(lbl);
                        vbcont.getChildren().add(hbbtns);
                        btnconf.setOnAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event){
                                stagep.close();
                                FXMLLoader loader=new FXMLLoader(getClass().getResource("ArmaTuHelado7.fxml"));
                                Parent rootf = null;
                                try{
                                    rootf = loader.load();
                                }catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                LocalDate fechahoy=LocalDate.now();
                                generarTransacción(pedidoaguardar.getIdPedido(),usuario.getNombre(),(ValorAPagar*1.22),fechahoy.toString(),"C");                                
                                ArmaTuHelado7Controller controlador=loader.getController();
                                Stage ventanafinal=new Stage();
                                controlador.initialize(pedidoaguardar,ventanafinal);
                                ventanafinal.setTitle("ArmaTuHelado7");
                                ventanafinal.setScene(new Scene(rootf));
                                ventanafinal.show();
                            }
                        });
                    }
                });
                rbt.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        lblex.setVisible(false);
                        vbcont.getChildren().clear();
                        vbcont.getChildren().add(lbltitulo);
                        vbcont.getChildren().add(hbops);
                        vbcont.getChildren().add(lbldetalle);
                        vbcont.getChildren().add(hbdetalles);
                        Locale.setDefault(Locale.US);
                        tfvalor.setText(String.format("%.2f", ValorAPagar));
                        tfvalor.setEditable(false);
                        tfadicional.setText(String.format("%.2f", ValorAPagar*0.10));
                        tfadicional.setEditable(false);
                        tfiva.setText(String.format("%.2f", ValorAPagar*1.10*0.12));
                        tfiva.setEditable(false);
                        tftotal.setText(String.format("%.2f", ValorAPagar*1.22));
                        tftotal.setEditable(false);
                        hbbtns.setPadding(new Insets(80,0,0,0));
                        Label lbl=new Label("Inserte los datos de su tarjeta:");
                        lbl.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                        HBox hbdatos=new HBox();
                        hbdatos.setPrefHeight(100);
                        hbdatos.setSpacing(35);
                        VBox vblabels2=new VBox();
                        vblabels2.setSpacing(5);
                        vblabels2.setPrefSize(100,200);
                        VBox vbtext2=new VBox();
                        vbtext2.setSpacing(5);
                        vbtext2.setPrefSize(200,200);
                        Label lblnombre=new Label("Nombre:");
                        lblnombre.setPrefHeight(25);
                        Label lblnumero=new Label("Número:");
                        lblnumero.setPrefHeight(25);
                        Label lblfecha=new Label("Fecha:");
                        lblfecha.setPrefHeight(25);
                        Label lblcvv=new Label("CVV:");
                        lblcvv.setPrefHeight(25);
                        TextField tfnombre=new TextField();
                        TextField tfnumero=new TextField();
                        DatePicker dpfecha=new DatePicker();
                        dpfecha.setPrefWidth(200);
                        TextField tfcvv=new TextField();
                        vbcont.getChildren().add(lbl);
                        vbcont.getChildren().add(hbdatos);
                        vbcont.getChildren().add(hbbtns);
                        hbdatos.getChildren().add(vblabels2);
                        hbdatos.getChildren().add(vbtext2);
                        vblabels2.getChildren().add(lblnombre);
                        vblabels2.getChildren().add(lblnumero);
                        vblabels2.getChildren().add(lblfecha);
                        vblabels2.getChildren().add(lblcvv);
                        vbtext2.getChildren().add(tfnombre);
                        vbtext2.getChildren().add(tfnumero);
                        vbtext2.getChildren().add(dpfecha);
                        vbtext2.getChildren().add(tfcvv);
                        btnconf.setOnAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event){
                                if(tfnombre.getText().isBlank()|tfnumero.getText().isBlank()|dpfecha.getValue()==null|tfcvv.getText().isBlank()){
                                    lblex.setVisible(true);
                                    throw new IncompleteStageException("No ha llenado todos los campos necesarios para continuar con su compra.", stagep);
                                }else{
                                    stagep.close();
                                    FXMLLoader loader=new FXMLLoader(getClass().getResource("ArmaTuHelado7.fxml"));
                                    Parent rootf = null;
                                    try {
                                        rootf = loader.load();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                    LocalDate fechahoy=LocalDate.now();
                                    generarTransacción(pedidoaguardar.getIdPedido(),usuario.getNombre(),(ValorAPagar*1.22),fechahoy.toString(),"C");
                                    ArmaTuHelado7Controller controlador=loader.getController();
                                    Stage ventanafinal=new Stage();
                                    controlador.initialize(pedidoaguardar,ventanafinal);
                                    ventanafinal.setTitle("ArmaTuHelado7");
                                    ventanafinal.setScene(new Scene(rootf));
                                    ventanafinal.show();
                                }
                            }
                        });
                    }
                });
            }
        });
    }
    public ArrayList<String> getListaPedidos(){
        File file=new File("src/main/resources/Proyecto2p/pedidos.txt");
        file.setReadable(true);
        file.setWritable(true);
        ArrayList<String> pedidos=new ArrayList();
        try(InputStream is=getClass().getResourceAsStream(PEDIDOS_PATH);
            BufferedReader br=new BufferedReader(new InputStreamReader(is))){
            String line;
            while((line=br.readLine())!=null){
                if(!pedidos.contains(line.split(",")[1]+","+line.split(",")[0])){
                    pedidos.add(line.split(",")[1]+","+line.split(",")[0]);
                }
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return pedidos;
    }
    @Override
    public void generarTransacción(String idPedido, String nombreCliente, double totalPagar, String fecha, String tipo) {
        File file=new File("src/main/resources/Proyecto2p/pagos.txt");
        file.setReadable(true);
        file.setWritable(true);
        ArrayList<String> pagos=new ArrayList();
        try(InputStream is=getClass().getResourceAsStream(PAGOS_PATH);
            BufferedReader br=new BufferedReader(new InputStreamReader(is))){
            String line;
            while((line=br.readLine())!=null){
                if(!pagos.contains(line.split(",")[0])){
                    pagos.add(line.split(",")[0]);
                }
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        Boolean b=true;
        while(b){
            int idPago = generarIdPago();
            if(!pagos.contains(idPago)){
                registrarPagoEnArchivo(idPago, idPedido, nombreCliente, totalPagar, fecha, tipo);
                cambiarEscena(idPedido);
                b=false;
            }
        }
    }
    private int generarIdPago() {
        return (int) (Math.random()*10000);
    }
    private void registrarPagoEnArchivo(int idPago, String idPedido, String nombreCliente, double totalPagar, String fecha, String tipo) {
        try{
            File file=new File("src/main/resources/Proyecto2p/pagos.txt");
            FileWriter fileWriter = new FileWriter(file,true);
            String registroPago = idPago + "," + idPedido + "," + nombreCliente + "," + totalPagar + "," + fecha + "," + tipo+"\n";
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(registroPago);
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void cambiarEscena(String idPedido) {
        System.out.println("Orden generada. Número de pedido: " + idPedido);
    }
}