package Proyecto2p;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class Locales {
    private Double coordenadaX;
    private Double coordenadaY;
    private String nombre;
    private String Horario;

    public Locales(Double coordenadaX, Double coordenadaY, String nombre, String Horario) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.nombre = nombre;
        this.Horario = Horario;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String Horario) {
        this.Horario = Horario;
    }
    public static ArrayList<Locales> getLocales() {
        ArrayList<Locales> locales = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Proyecto2p/locales.txt",Charset.forName("UTF-8")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                Double coordenadasX = Double.parseDouble(datos[0]);
                Double coordenadasY = Double.parseDouble(datos[1]);
                String nombre = datos[2].trim();
                String horario = datos[3].trim();
                locales.add(new Locales(coordenadasX, coordenadasY, nombre, horario));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
     
      return locales; 
    }
}
