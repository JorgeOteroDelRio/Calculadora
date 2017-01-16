/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorclient;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author jota
 */
public class CalculatorClient {

    static int operando1,operando2,operacion;
    
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Introduzca el primer operando: ");
        operando1 = input.nextInt();
        System.out.println("Introduzca el segundo operando: ");
        operando2 = input.nextInt();
        do{
            System.out.println("Introduzca la operación (0-Suma, 1-Resta, 2-Multiplicación, 3-División): ");
            operacion = input.nextInt();
            if(operacion < 0 || operacion > 3){
                System.out.println("Operando no válido, introduzca otro");
            }
        }while(operacion < 0 || operacion > 3);
        
        
        Socket client = new Socket();
        InetSocketAddress addr = new InetSocketAddress("localhost",6000);
        System.out.println("Conectando al servidor...");
        client.connect(addr);
        System.out.println("Conexión establecida");
        OutputStream os = client.getOutputStream();
        os.write(operando1);
        os.write(operando2);
        os.write(operacion);
        System.out.println("Datos enviados");
        System.out.println("Cerrando conexión al servidor");
        client.close();
    }
    
}
