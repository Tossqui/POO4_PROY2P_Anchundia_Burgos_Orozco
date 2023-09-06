/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Proyecto2p;

/**
 *
 * @author Dario Anchundia Cobo
 */
public interface Pagable {
    void generarTransacción(String idPedido, String nombreCliente, double totalPagar, String fecha, String tipo);
}
