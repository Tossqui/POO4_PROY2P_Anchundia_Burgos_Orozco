/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto2p;

import java.util.ArrayList;

/**
 *
 * @author Thomas Burgos
 */
public class PedidoActual{
    Base base;
    ArrayList<Sabor> sabores;
    ArrayList<Topping> toppings;

    public PedidoActual(Base base,ArrayList<Sabor> sabores,ArrayList<Topping> toppings){
        this.base=base;
        this.sabores=sabores;
        this.toppings=toppings;
    }

    @Override
    public String toString() {
        return "PedidoGenerado{" + "base=" + base + ", sabores=" + sabores + ", toppings=" + toppings + '}';
    }
    
}
