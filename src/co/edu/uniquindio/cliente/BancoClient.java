package co.edu.uniquindio.cliente;
import java.net.Socket;

public class BancoClient {
    public static final int PORT = 3400;
    public static final String SERVER = "localhost";  
    public static boolean IteradorInfinito;
    
    

    private Socket clientSideSocket;

    public BancoClient() { 
    	System.out.println("Banco TCP Cliente..."); 
    	System.out.println();
    }

    public void init() throws Exception {
    	createMenu();
    	IteradorInfinito=true;
    	
    	while(IteradorInfinito) {
    	clientSideSocket =new Socket (SERVER, PORT);
        BancoClientProtocol.protocol(clientSideSocket);
        clientSideSocket.close();
        
    	}
    	
    }

    public static void main(String args[]) throws Exception {

        BancoClient ec = new BancoClient();
        ec.init();

    }
    public static void createMenu() {
    	System.out.print("Seleccione la transaccion a realizar: " + "\n" + 
    					 "[1] Abrir una cuenta de ahorros" + "\n" +
    					 "[2] Crear un bolsillo"  + "\n" +
    					 "[3] Cancelar un bolsillo" + "\n" +
    					 "[4] Cancelar una cuenta de ahorros" + "\n" +
    					 "[5] Depositar dinero en una cuenta" + "\n" +
    					 "[6] Retirar dinero de una cuenta" + "\n" +
    					 "[7] Trasladar dinero a un bolsillo" + "\n" +
    					 "[8] Consultar saldo de una cuenta o un bolsillo  \n" + 
    					 "[9] Cargar transacciones \n"+ 
    					 "[10] Salir \n");
    	
    	
    	
    }
}
