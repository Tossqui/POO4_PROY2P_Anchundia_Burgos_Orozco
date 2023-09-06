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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Dario Anchundia Cobo
 */
public class ArmaTuHeladoController {
    @FXML
    Label lblex;
    @FXML
    private AnchorPane rootArmaTuHelado;
    @FXML
    private ImageView imagenDeFondo;
    @FXML
    private VBox contenedorPrincipal;
    @FXML
    private Label tituloLabel;
    @FXML
    private HBox hbopciones;
    @FXML
    private VBox vbop1;
    @FXML
    private HBox hbimgv1;
    @FXML
    private ImageView imagenOpcion1;
    @FXML
    private Label lblimgv1;
    @FXML
    private Label lblop1;
    @FXML
    private VBox vbop2;
    @FXML
    private HBox hbimgv2;
    @FXML
    private ImageView imagenOpcion2;
    @FXML
    private Label lblimgv2;
    @FXML
    private Label lblop2;
    @FXML
    private VBox vbop3;
    @FXML
    private HBox hbimgv3;
    @FXML
    private ImageView imagenOpcion3;
    @FXML
    private Label lblimgv3;
    @FXML
    private Label lblop3;
    @FXML
    private HBox hbcontinuar;
    @FXML
    private Label valorAPagarLabel;
    @FXML
    private Button btncontinuar;
    double ValorAPagar;
    String BASES_PATH="/Proyecto2p/bases.txt";
    PedidoActual pedido=new PedidoActual(null,new ArrayList<>(),new ArrayList<>());
    @FXML
    public void initialize(Usuario usuario){
        tituloLabel.setFont(new Font(24.0));
        Image imagenFondo=new Image("/Proyecto2p/haztuheladofondo.jpg");
        Image imagen1=new Image("/Proyecto2p/Yogurt.png");
        Image imagen2=new Image("/Proyecto2p/Helado.png");
        Image imagen3=new Image("/Proyecto2p/Vegano.png");
        imagenDeFondo.setImage(imagenFondo);
        imagenOpcion1.setImage(imagen1);
        imagenOpcion2.setImage(imagen2);
        imagenOpcion3.setImage(imagen3);    
        ArrayList<Base> bases=obtenerBases();
        lblimgv1.setText(bases.get(0).base.substring(0, 1).toUpperCase()+bases.get(0).base.substring(1));
        lblimgv2.setText(bases.get(1).base.substring(0, 1).toUpperCase()+bases.get(1).base.substring(1));
        lblimgv3.setText(bases.get(2).base.substring(0, 1).toUpperCase()+bases.get(2).base.substring(1));
        lblop1.setText(bases.get(0).precio);
        lblop2.setText(bases.get(1).precio);
        lblop3.setText(bases.get(2).precio);
        
        hbimgv1.setOnMouseClicked(new EventHandler<MouseEvent>() {            
            @Override
            public void handle(MouseEvent event) {
                valorAPagarLabel.setText("Valor a pagar: "+String.format("%.2f", Double.parseDouble(bases.get(0).precio)));    
                ValorAPagar=Double.parseDouble(bases.get(0).precio);
                pedido.base=new Base(bases.get(0).base.substring(0, 1).toUpperCase()+bases.get(0).base.substring(1),bases.get(0).precio);
            }
        });
        hbimgv2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                valorAPagarLabel.setText("Valor a pagar: "+String.format("%.2f", Double.parseDouble(bases.get(1).precio)));    
                ValorAPagar=Double.parseDouble(bases.get(1).precio);
                pedido.base=new Base(bases.get(1).base.substring(0, 1).toUpperCase()+bases.get(1).base.substring(1),bases.get(1).precio);
            }
        });
        hbimgv3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                valorAPagarLabel.setText("Valor a pagar: "+String.format("%.2f", Double.parseDouble(bases.get(2).precio)));        
                ValorAPagar=Double.parseDouble(bases.get(2).precio);
                pedido.base=new Base(bases.get(2).base.substring(0, 1).toUpperCase()+bases.get(2).base.substring(1),bases.get(2).precio);
            }
        });
        btncontinuar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(ValorAPagar==0){
                    Stage s=(Stage)btncontinuar.getScene().getWindow();
                    lblex.setVisible(true);
                    throw new IncompleteStageException("Debes elegir una base para continuar.", s);
                }else{
                    Stage stage=(Stage)rootArmaTuHelado.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("ArmaTuHelado2.fxml"));
                    Parent ArmaTuHeladoRoot = null;
                    try {
                        ArmaTuHeladoRoot = loader.load();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    ArmaTuHelado2Controller controlador=loader.getController();
                    controlador.initialize(usuario,ValorAPagar,pedido);
                    Stage nuevaVentana=new Stage();
                    nuevaVentana.setTitle("ArmaTuHelado2.fxml");
                    nuevaVentana.setScene(new Scene(ArmaTuHeladoRoot));
                    nuevaVentana.show();
                }
                
            }
        });
    }    
    public ArrayList<Base> obtenerBases(){
        ArrayList<Base> bases=new ArrayList<>();
        try(InputStream is=getClass().getResourceAsStream(BASES_PATH);
            BufferedReader br=new BufferedReader(new InputStreamReader(is))){
            String line;
            while((line=br.readLine())!=null){
                String[] datos=line.split(",");
                bases.add(new Base(datos[0],datos[1]));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return bases;
    }
}
