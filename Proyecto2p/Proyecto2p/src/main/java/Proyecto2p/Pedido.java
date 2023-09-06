/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto2p;

import java.io.Serializable;

/**
 *
 * @author Dario Anchundia Cobo
 */
public class Pedido implements Serializable{
    private String idPedido;
    private String nombreCliente;
    private double valor;

    public Pedido(String idPedido, String nombreCliente, double valor) {
        this.idPedido = idPedido;
        this.nombreCliente = nombreCliente;
        this.valor = valor;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public double getValor() {
        return valor;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Pedido{" + "idPedido=" + idPedido + ", nombreCliente=" + nombreCliente + ", valor=" + valor + '}';
    }
    
}
