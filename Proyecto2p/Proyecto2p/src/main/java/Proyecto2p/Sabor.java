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
 * @author Thomas Burgos
 */
public class Sabor{
    String sabor;
    String precio;
    public Sabor(String sabor,String precio){
        this.sabor=sabor;
        this.precio=precio;
    }

    @Override
    public String toString() {
        return "Sabor{" + "sabor=" + sabor + ", precio=" + precio + '}';
    }
    
}
