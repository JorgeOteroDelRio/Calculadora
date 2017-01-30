/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorserver;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
/**
 *
 * @author jota
 */
public class CalculatorServer {
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(); //Creo socket para el servidor
        SocketAddress addr = new InetSocketAddress("localhost", 6000); //Le asigno una ip y un puerto al servidor
        server.bind(addr); //Le digo al socket que se ponga a la escucha de peticiones
        System.out.println("Servidor escuchando peticiones...");
        while(true){
            Socket conexion = server.accept(); //Que acepte una conexión entrante
            Cliente c = new Cliente(conexion); //Crea un hilo por cada cliente conectado
            c.start(); //Ejecuto el hilo cliente
        }
        
    }
    
}
