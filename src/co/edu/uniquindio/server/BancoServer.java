package co.edu.uniquindio.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import co.edu.uniquindio.banco.Cuenta;
import co.edu.uniquindio.exceptions.BancoException;

public class BancoServer {
    public static final int PORT = 3400;
    public static HashMap<String, Cuenta> clientes;

    private ServerSocket listener; private Socket serverSideSocket;

    public BancoServer() {
        System.out.println("Servidor del banco corriendo en el puerto: " + PORT);
        getHash();
    }
    
    public void init() throws Exception {
        listener = new ServerSocket (PORT);
        while (true) {
            serverSideSocket =listener.accept();
            BancoServerProtocol.protocol(serverSideSocket);
        }
    }
    
    public static HashMap<String, Cuenta> getHash(){
    	if(clientes!=null) {
    		return clientes;
    	}else {
    		clientes = new HashMap<>();
    	}
    	
    	return clientes;
    }
    
    /**
     * Metodo que verifica que si ya existe una cuenta con un numero de cuenta dado
     * @param nombre
     * @return
     */
    public static boolean alreadyExists(String nombre) {
    	if(clientes.get(nombre)!=null)
    		return true;
    	else
	    	return false;
    }
    
    
    public static Cuenta obtenerCuenta(int numCuenta) throws BancoException
    {
    	for (Map.Entry<String, Cuenta> entry : clientes.entrySet()) {
    	    if(entry.getValue().getNumCuenta() == numCuenta)
    	    {
    	    	return entry.getValue();
    	    }
    	}
    	throw new BancoException("No existe una cuenta con ese numero");
    }
    
    
    public static boolean isNumber(String number) {
    	try {
    		Integer.parseInt(number);
			
		} catch (NumberFormatException e) {
			return false;
		}
    	
    	return true;
    }
    
   
    	public static void main(String[] args) throws Exception {
    		BancoServer banco = new BancoServer();
    		banco.init();
    	}
    
}
