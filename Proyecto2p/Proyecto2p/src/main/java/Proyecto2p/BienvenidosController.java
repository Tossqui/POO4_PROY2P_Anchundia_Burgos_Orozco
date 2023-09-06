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
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

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
    private VBox vbpedidos;
    private static final String USUARIOS_PATH="/Proyecto2p/usuarios.txt";
    private static final String PEDIDOS_PATH="/Proyecto2p/pedidos.txt";
    Usuario u;
    private ArrayList<Locales> loc;
    private ArrayList<Integer> tiemposDeEspera = new ArrayList<>();
    private Pane contenedor = new Pane();
    public void initialize(Usuario usuario){
        u=usuario;
        Image image=new Image(getClass().getResourceAsStream("/Proyecto2p/bienvenidosfondo.jpg"));
        imagenfondo.setImage(image);
        switch(usuario.getGenero()){
            case "M":
                lblwelcome.setFont(Font.font("System",FontWeight.BOLD,24));
                lblwelcome.setText("Bienvenido, "+usuario.getNombre().split(" ")[0]);
                lblwelcome.setTextFill(Color.BROWN);
                break;
            case "F":
                lblwelcome.setFont(Font.font("System",FontWeight.BOLD,24));
                lblwelcome.setText("Bienvenida, "+usuario.getNombre().split(" ")[0]);
                lblwelcome.setTextFill(Color.BROWN);
                break;
        }
        FXMLLoader loader=new FXMLLoader(getClass().getResource("PedidosGenerados.fxml"));
        Parent rootpedidos = null;
        try {
            rootpedidos = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PedidosGeneradosController controlador=loader.getController();
        controlador.initialize();
        Stage nuevaVentana=new Stage();
        nuevaVentana.setTitle("PedidosGenerados");
        nuevaVentana.setScene(new Scene(rootpedidos));
        nuevaVentana.show();
        loc= Locales.getLocales();
    }
    @FXML
    private void EncuentraNuestrosLocales(){
        Stage ventana = new Stage();
        ventana.setTitle("Mapa");
        
        Image imagenFondo = new Image(getClass().getResourceAsStream("/Proyecto2p/mapa2.png"),620,460,false,false); 
        ImageView imageView = new ImageView(imagenFondo);
        contenedor.getChildren().add(imageView);
        //se cargo la imagen del mapa
        //muestra de locales
        Random rd = new Random();
        for (int i = 0; i < loc.size(); i++) {
            tiemposDeEspera.add(rd.nextInt(10) + 1);
        }
        
        
        var timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> mostrarSiguienteImagen()));
        timeline.setCycleCount(tiemposDeEspera.size());
        timeline.play();
        Scene escena = new Scene(contenedor, 620,460 ); 
        ventana.setScene(escena);
        ventana.show();
    }
    @FXML
    private void HazTuPedido() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Proyecto2p/ArmaTuHelado.fxml"));
        Parent ArmaTuHeladoRoot=loader.load();
        ArmaTuHeladoController controlador=loader.getController();
        controlador.initialize(u);
        Stage nuevaVentana=new Stage();
        nuevaVentana.setTitle("ArmaTuHelado.fxml");
        nuevaVentana.setScene(new Scene(ArmaTuHeladoRoot));
        nuevaVentana.show();
        
    }
    private void mostrarDetalleLocal(Locales local) {
    VBox root = new VBox();
    root.setAlignment(Pos.CENTER);
    BackgroundFill backgroundFill = new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
    Background background = new Background(backgroundFill);
    root.setBackground(background);
    root.setSpacing(10);
    
    // Crear los elementos de la ventana emergente
    Label lbl = new Label("Heladeria Gioletti");
    lbl.setStyle("-fx-font-weight: bold;");
    Label nombreLabel = new Label("Nombre: " + local.getNombre());
    Label horarioLabel = new Label("Horario: " + local.getHorario());
    Label timeLabel = new Label("Cerrando en 5 segundos...");
    Button cerrarButton = new Button("Cerrar");
    cerrarButton.setStyle("-fx-background-color: linear-gradient(to bottom, #ffde59, #ff914d);");
    cerrarButton.setOnAction(event -> {
    cerrarButton.getScene().getWindow().hide();
    });
    root.getChildren().add(lbl);
    root.getChildren().add(nombreLabel);
    root.getChildren().add(horarioLabel);
    root.getChildren().add(cerrarButton);
    root.getChildren().add(timeLabel);
    Stage newStage = new Stage();
    crearThreadNuevaVentana(timeLabel,newStage);
    Scene newScene = new Scene(root, 250, 200);
    newStage.setScene(newScene);
    newStage.show();
}
    private void crearThreadNuevaVentana(Label timeLabel, Stage stage) {
    Thread counterThread = new Thread(() -> {
        for (int i = 5; i >= 0; i--) {
            int seconds = i;
            Platform.runLater(() -> {
                if (seconds == 0) {
                    // Cuando llegue a 0, cierra la ventana
                    timeLabel.setText("Tiempo transcurrido: 0 segundos");
                    stage.close();
                } else {
                    timeLabel.setText("Tiempo restante: " + seconds + " segundos");
                }
            });
            try {
                Thread.sleep(1000); // Dormir durante 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    counterThread.start();

    
}
    private void mostrarSiguienteImagen(){
        Image image2 = new Image(getClass().getResourceAsStream("/Proyecto2p/local.png"),50,50,false,false); 
        ImageView imageView2 = new ImageView(image2);
        if (!loc.isEmpty() && !tiemposDeEspera.isEmpty()) {
            Locales local = loc.remove(0);
            int tiempoDeEspera = tiemposDeEspera.remove(0);
            

            // Muestra la imagen de local en el mapa
            imageView2.setLayoutX(local.getCoordenadaX());
            imageView2.setLayoutY(local.getCoordenadaY());
            contenedor.getChildren().add(imageView2);
            imageView2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mostrarDetalleLocal(local);
                }
            });
        }
    }
}
