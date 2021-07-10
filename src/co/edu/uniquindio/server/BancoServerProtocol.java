package co.edu.uniquindio.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import co.edu.uniquindio.banco.Bolsillo;
import co.edu.uniquindio.banco.Cuenta;
import co.edu.uniquindio.exceptions.BancoException;
import co.edu.uniquindio.resources.Loader;

public class BancoServerProtocol {
    private  static PrintWriter toNetwork;
    private static BufferedReader fromNetwork;

    public static void protocol(Socket socket) throws IOException, InterruptedException {
        createStreams (socket);
        String message= fromNetwork.readLine();
        System.out.println(" [Server] From client: " + message);
        try {
			realizarOperacion(message);
		} catch (BancoException e) {
			toNetwork.println("Error: " + e.getMessage());
		}
        
        
       

    }

    private static void createStreams (Socket socket) throws IOException {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    
    /**
     * Método principal de los servicios
     * @param message Comando ingresado por el cliente
     * @throws BancoException 
     * @throws InterruptedException 
     */
    public static void realizarOperacion(String message) throws BancoException, InterruptedException {
    	String[] messageArray = message.split(" ");
    	
    	if(messageArray[0].equals("ABRIR_CUENTA")) {
    		abrirCuenta(messageArray[1]);
            toNetwork.println("Apertura de la cuenta exitosa");
            
    	}else if(messageArray[0].equals("ABRIR_BOLSILLO")){
    		abrirBolsillo(messageArray[1]);
    		toNetwork.println("Apertura del bolsillo exitosa");
    		
    	}else if(messageArray[0].equals("CANCELAR_BOLSILLO")){
    		cancelarBolsillo(messageArray[1]);
    		toNetwork.println("Cancelación del bolsillo exitosa");
    		
    	}else if(messageArray[0].equals("CANCELAR_CUENTA")){
    		cancelarCuenta(messageArray[1]);
    		toNetwork.println("Cancelación de la cuenta exitosa");
    		
    	}else if(messageArray[0].equals("DEPOSITAR")){
    		depositarDinero(messageArray[1], messageArray[2]);
    		toNetwork.println("Deposito de " +messageArray[2] + " exitoso.");
    		
    	}else if(messageArray[0].equals("RETIRAR")){
    		
    		retirarDinero(messageArray[1], messageArray[2]);
    		toNetwork.println("Retiro de " +messageArray[2] + " exitoso.");
    		
    	}else if(messageArray[0].equals("TRASLADAR")){
    		trasladarDinero(messageArray[1], messageArray[2]);
    		toNetwork.println("Traslado al bolsillo de " +messageArray[2] + " exitoso.");
    		
    	}else if(messageArray[0].equals("CONSULTAR")){
    		toNetwork.println("Su saldo de " + messageArray[1] + " es de: " + consultarSaldo(messageArray[1]));
    	}else if(messageArray[0].equals("SALIR")){
    		toNetwork.println("¡Hasta luego! Vuelve pronto.");
    	}else if(messageArray[0].equals("ERROR_CUENTA")){
    		toNetwork.println("Por favor ingrese un numero de cuenta valido!");
    	}else if(messageArray[0].equals("ERROR_SALDO")){
    		toNetwork.println("Por favor ingrese un número valido en el saldo!");
    	}else if(messageArray[0].equals("CARGAR")){
    		cargarTransacciones(messageArray[1]);
    		toNetwork.println("Transacciones cargadas correctamente!");
    		
    	}else {
    		toNetwork.println("No se encuentra el comando ingresado.");
    	}
    		
    }
    
    public static void cargarTransacciones(String mensaje) throws BancoException{
    	ArrayList<String> lista = Loader.cargarTransacciones(mensaje);
    	if(lista==null)
    		throw new BancoException("Por favor verifique el nombre ingresado.");
    	
    	
    }
    /**
     * Servicio #1 del enunciado del proyecto
     * @param nombre nombre de usuario del cliente
     * @throws BancoException
     */
    public static void abrirCuenta(String nombre) throws BancoException  {
    	if(BancoServer.alreadyExists(nombre.toLowerCase())) 
    		throw new BancoException("Ya existe una cuenta con el nombre: " + nombre);
    	
    	Cuenta cuenta = new Cuenta(BancoServer.getHash().size()+1, nombre.toLowerCase(), 0);
    	BancoServer.getHash().put(nombre.toLowerCase(), cuenta);
    	
    	
    }
    
    /**
     * Servicio #2 del enunciado del proyecto
     * @param numCuenta Numero de cuenta a la cual se le abrirá el bolsillo
     * @throws BancoException
     */
    public static void abrirBolsillo(String numCuenta) throws BancoException  {
    	
    	int num = Integer.parseInt(numCuenta);
    	Cuenta cuenta = BancoServer.obtenerCuenta(num);
    	if(cuenta.getBolsillo() != null)
    		throw new BancoException("Ya existe un bolsillo con la cuenta: " + numCuenta);
    	
    	Bolsillo bolsillo = new Bolsillo( num+ "b", cuenta.getNombreUsuario(), 0);
    	cuenta.setBolsillo(bolsillo);
    	
 
    }
    
    /**
     * Servicio #3 del enunciado del proyecto
     * @param numCuenta Numero de cuenta a la cual se le cancelará el bolsillo
     * @throws BancoException
     */
    public static void cancelarBolsillo(String numCuenta) throws BancoException  {
    	
    	Cuenta cuenta =  BancoServer.obtenerCuenta(Integer.parseInt(numCuenta.substring(0, numCuenta.length()-1)));
    	
    	if(cuenta.getBolsillo() == null)
    		throw new BancoException("No existe un bolsillo en esta cuenta.");
    	
    	cuenta.setSaldo(cuenta.getBolsillo().getSaldo() +  cuenta.getSaldo());
    	cuenta.setBolsillo(null);
    	
    }
    
    /**
     * Servicio #4 del enunciado del proyecto
     * @param numCuenta Numero de cuenta a la cual se le cancelará el bolsillo
     * @throws BancoException
     */
    public static void cancelarCuenta(String numCuenta) throws BancoException {
    	Cuenta cuenta =  BancoServer.obtenerCuenta(Integer.parseInt(numCuenta));
    	
    	if(cuenta == null)
    		throw new BancoException("No se puede cancelar porque la cuenta no existe.");
    	
    	if(cuenta.getBolsillo() != null)
    		throw new BancoException("No se puede cancelar la cuenta por que tiene un bolsillo asociado");
    	
    	if(cuenta.getSaldo() != 0)
    		throw new BancoException("No se puede cancelar la cuenta por que tiene saldo en la cuenta");
    	
    	BancoServer.getHash().remove(cuenta.getNombreUsuario());
    }
    
    /**
     * Servicio #5 del enunciado del proyecto
     * @param numCuenta
     * @param valor
     * @throws BancoException 
     * @throws NumberFormatException 
     */
    public static void depositarDinero(String numCuenta, String valor) throws NumberFormatException, BancoException {
    	Cuenta cuenta =  BancoServer.obtenerCuenta(Integer.parseInt(numCuenta));
    	
    	if(cuenta == null)
    		throw new BancoException("No se puede depositar porque la cuenta no existe.");
    	
    	cuenta.setSaldo(cuenta.getSaldo() + Integer.parseInt(valor));
    }
    
    /**
     * Servicio #6 del enunciado del proyecto
     * @param numCuenta
     * @param valor
     * @throws BancoException 
     */
    public static void retirarDinero(String numCuenta, String valor) throws BancoException {
    	Cuenta cuenta =  BancoServer.obtenerCuenta(Integer.parseInt(numCuenta));
    	if(cuenta == null)
    		throw new BancoException("No se puede retirar porque la cuenta no existe.");
    	
    	if(cuenta.getSaldo()<Integer.parseInt(valor))
    		throw new BancoException("No posee saldo suficiente para realizar el retiro.");
    	
    	cuenta.setSaldo(cuenta.getSaldo() - Integer.parseInt(valor));
    	
    }
    
    /**
     * Servicio #7 del enunciado del proyecto
     * @param numCuenta
     * @param valor
     * @throws BancoException 
     */
    public static void trasladarDinero(String numCuenta, String valor) throws NumberFormatException, BancoException {
    	Cuenta cuenta =  BancoServer.obtenerCuenta(Integer.parseInt(numCuenta));
    	
    	if(cuenta == null)
    		throw new BancoException("No se puede trasladar porque la cuenta no existe.");
    	
    	if(cuenta.getSaldo()<Integer.parseInt(valor))
    		throw new BancoException("No posee saldo suficiente para realizar el retiro.");
    	
    	if(cuenta.getBolsillo() == null)
    		throw new BancoException("No se puede trasladar dinero porque no tiene un bolsillo asociado");
    	
    	
    	cuenta.setSaldo(cuenta.getSaldo() - Integer.parseInt(valor));
    	cuenta.getBolsillo().setSaldo(cuenta.getBolsillo().getSaldo() + Integer.parseInt(valor));
    	
    	
    }
    
    /**
     * Servicio #8 del enunciado del proyecto
     * @param numCuenta
     * @return
     * @throws NumberFormatException
     * @throws BancoException
     */
    public static double consultarSaldo(String numCuenta) throws NumberFormatException, BancoException {
    	Cuenta cuenta=null;
    	if(BancoServer.isNumber(numCuenta)) {
    		
    		cuenta =  BancoServer.obtenerCuenta(Integer.parseInt(numCuenta));
    		if(cuenta == null)
        		throw new BancoException("No se puede trasladar porque la cuenta no existe.");
    		
    		return cuenta.getSaldo();
    		
    	}else {
    		cuenta =  BancoServer.obtenerCuenta(Integer.parseInt(numCuenta.substring(0, numCuenta.length()-1)));
    		if(cuenta == null)
        		throw new BancoException("No se puede trasladar porque la cuenta no existe.");
    		
    		if(cuenta.getBolsillo() == null)
        		throw new BancoException("No tiene un bolsillo asociado");
    			
    			return cuenta.getBolsillo().getSaldo();
    	}
    	
    	
    
    		
    		
        
    }
    
    
    }


