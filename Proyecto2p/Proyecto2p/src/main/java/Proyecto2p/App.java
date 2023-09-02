package Proyecto2p;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage Login) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proyecto2p/LoginView.fxml"));       //path
            Login.setScene(new Scene(loader.load()));
            Login.setTitle("Login");
            Login.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void openBienvenidos(){
        try{
            FXMLLoader loader=new FXMLLoader();       //path
            loader.setLocation(App.class.getResource("/Proyecto2p/Bienvenidos.fxml"));
            Stage bienvenidos=new Stage();
            Scene scene = new Scene(loader.load());
            bienvenidos.setScene(scene);
            bienvenidos.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}