/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto2p;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dario Anchundia Cobo
 */
public class Topping {
    String topping;
    String precio;
    public Topping(String topping,String precio){
        this.topping=topping;
        this.precio=precio;
    }

    @Override
    public String toString() {
        return "Topping{" + "topping=" + topping + ", precio=" + precio + '}';
    }
    
}