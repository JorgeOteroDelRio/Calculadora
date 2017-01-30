/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
 *
 * @author jota
 */
public class Cliente extends Thread{
    
    private static int nclientes = 0; //Número de clientes conectados
    private Socket cliente; //Socket del cliente
    private int operando1,operando2,operacion,resultado; //Datos que enviaremos al servidor
    private InputStream is; //Flujo de entrada al cliente desde el servidor
    private OutputStream os; //Flujo de salida hacia el servidor
    
    public Cliente(Socket cliente){
        nclientes++; //Incremento el número de clientes conectados
        super.setName("Cliente " + String.valueOf(nclientes)); //Le cambio el nombre al hilo
        this.cliente = cliente;
        try{
            this.is=cliente.getInputStream();
            this.os=cliente.getOutputStream();
        }catch(IOException e){
            System.out.println("Error al crear flujos");
        }
    }

    @Override
    public void run() {
        while(cliente.isConnected()){ //Mientras el cliente esté conectado
            try {
                if(is.available()>0){ //Si hay datos sin leer en el flujo de entrada
                    try{
                        operando1 = is.read(); //Leo los datos
                        operando2 = is.read();
                        operacion = is.read();
                        System.out.println("El operando 1 es: " + operando1);
                        System.out.println("El operando 2 es: " + operando2);
                        switch(operacion){ //Compruebo la operación elegida por el cliente
                            case 0:
                                System.out.println("La operación es suma");
                                resultado = operando1 + operando2;
                                System.out.println("El resultado es: " + operando1 + " + "
                                        + operando2 + " = " + resultado);
                                
                                break;
                            case 1:
                                System.out.println("La operación es resta");
                                resultado = operando1 - operando2;
                                System.out.println("El resultado es: " + operando1 + " - "
                                        + operando2 + " = " + resultado);
                                break;
                            case 2:
                                System.out.println("La operación es multiplicación");
                                resultado = operando1 * operando2;
                                System.out.println("El resultado es: " + operando1 + " * "
                                        + operando2 + " = " + resultado);
                                break;
                            default:
                                System.out.println("La operación es división");
                                resultado = operando1 / operando2;
                                System.out.println("El resultado es: " + operando1 + " / "
                                        + operando2 + " = " + resultado);
                        }
                        os.write(resultado); //Le mando el resultado de la operación al flujo de salida
                        //para que el cliente lo pueda leer
                    }catch(IOException e){
                        System.out.println("Error al recibir los datos del cliente");
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error con el flujo de entrada");
            }
            
        }
    }
    
    
}
