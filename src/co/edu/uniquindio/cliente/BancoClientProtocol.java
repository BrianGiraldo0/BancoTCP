package co.edu.uniquindio.cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import co.edu.uniquindio.banco.Cuenta;
import co.edu.uniquindio.server.BancoServer;

public class BancoClientProtocol {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static PrintWriter toNetwork;

    private static BufferedReader fromNetwork;

    public static void protocol (Socket socket) throws Exception {
    	String fromUser="";
        createStreams(socket);
        
        	 System.out.print("Ingrese una opción: ");
             fromUser = SCANNER.nextLine();
             
             toNetwork.println(readMenu(fromUser));

             String fromServer = fromNetwork.readLine();
             System.out.println("[Client] From server: " + fromServer);
       


    }
    private static void createStreams (Socket socket) throws IOException {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    
    public static String readMenu(String option) {
    	int opt = Integer.parseInt(option);
    	String numCuenta = "";
    	String command ="";
    	String saldo = "";
    	switch (opt) {
		case 1: System.out.print("Ingrese el nombre de usuario: ");
				String usuario = SCANNER.nextLine();
				command = "ABRIR_CUENTA " + usuario;
			break;
			
		case 2: System.out.print("Ingrese el numero de cuenta: ");
        		numCuenta = SCANNER.nextLine();
        		if(BancoServer.isNumber(numCuenta))
				command = "ABRIR_BOLSILLO " + numCuenta	;
        		else command = "ERROR_CUENTA ";
			break;
			
		case 3: System.out.print("Ingrese el numero de cuenta del bolsillo: ");
				numCuenta = SCANNER.nextLine();
				command = "CANCELAR_BOLSILLO " + numCuenta;
			break;
			
		case 4: System.out.print("Ingrese el numero de cuenta: ");
				numCuenta = SCANNER.nextLine(); 
				if(BancoServer.isNumber(numCuenta))
				command = "CANCELAR_CUENTA " + numCuenta;
				else command = "ERROR_CUENTA ";
			break;
			
		case 5: System.out.print("Ingrese el numero de cuenta: ");
		        numCuenta = SCANNER.nextLine();
				System.out.print("Ingrese el saldo a depositar: ");
				saldo = SCANNER.nextLine();
				if(BancoServer.isNumber(numCuenta)) {
					if(BancoServer.isNumber(saldo))
						command = "DEPOSITAR " + numCuenta + " " + saldo;
					else command = "ERROR_SALDO ";
				} else command = "ERROR_CUENTA ";
			break;
			
		case 6: System.out.print("Ingrese el numero de cuenta: ");
        		numCuenta = SCANNER.nextLine();
				System.out.print("Ingrese el saldo a retirar: ");
				saldo = SCANNER.nextLine(); 
				if(BancoServer.isNumber(numCuenta)) {
					if(BancoServer.isNumber(saldo))
						command = "RETIRAR " + numCuenta + " " + saldo;
					else command = "ERROR_SALDO ";
				} else command = "ERROR_CUENTA ";
				
			break;
			
		case 7: System.out.print("Ingrese el numero de cuenta: ");
				numCuenta = SCANNER.nextLine();
				System.out.print("Ingrese el saldo a trasladar: ");
				saldo = SCANNER.nextLine(); 
				if(BancoServer.isNumber(numCuenta)) {
					if(BancoServer.isNumber(saldo))
						command = "TRASLADAR " + numCuenta + " " + saldo;
					else command = "ERROR_SALDO ";
				} else command = "ERROR_CUENTA ";
				
			break;
			
		case 8: System.out.print("Ingrese el numero de cuenta o del bolsillo: ");
				numCuenta = SCANNER.nextLine(); 
				command = "CONSULTAR " + numCuenta;
			break;
		
		case 9: BancoClient.IteradorInfinito = false;
				command= "SALIR";

		}
    	
    	return command;
    }
}
