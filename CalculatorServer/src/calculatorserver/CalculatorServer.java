/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorserver;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
/**
 *
 * @author jota
 */
public class CalculatorServer {

    private static int operando1,operando2,operacion;
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        SocketAddress addr = new InetSocketAddress("localhost", 6000);
        server.bind(addr);
        System.out.println("Servidor escuchando peticiones...");
        Socket conexion = server.accept();
        InputStream is = conexion.getInputStream();
        operando1 = is.read();
        operando2 = is.read();
        operacion = is.read();
        System.out.println("El operando 1 es: " + operando1);
        System.out.println("El operando 2 es: " + operando2);
        switch(operacion){
            case 0:
                System.out.println("La operación es suma");
                System.out.println("El resultado es: " + operando1 + " + " 
                        + operando2 + " = " + (operando1+operando2));
                break;
            case 1:
                System.out.println("La operación es resta");
                System.out.println("El resultado es: " + operando1 + " - " 
                        + operando2 + " = " + (operando1-operando2));
                break;
            case 2:
                System.out.println("La operación es multiplicación");
                System.out.println("El resultado es: " + operando1 + " * " 
                        + operando2 + " = " + (operando1*operando2));
                break;
            default:
                System.out.println("La operación es división");
                System.out.println("El resultado es: " + operando1 + " / " 
                        + operando2 + " = " + (operando1/operando2));
        }
    }
    
}
