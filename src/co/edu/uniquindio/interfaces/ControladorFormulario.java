package co.edu.uniquindio.interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import co.edu.uniquindio.banco.ClientApp;
import co.edu.uniquindio.banco.Transaccion;
import co.edu.uniquindio.cliente.BancoClient;
import co.edu.uniquindio.server.BancoServer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorFormulario {

    private static PrintWriter toNetwork;

    private static BufferedReader fromNetwork;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label tituloOperacionLabel;

    @FXML
    private TextField operacion1Text;

    @FXML
    private Label operacion1Label;

    @FXML
    private Button btnContinuar;

    @FXML
    private Button btnSalir;

    @FXML
    private Label operacion2Label;

    @FXML
    private TextField operacion2Text;
    
    private String command="";
    private int opcion;
    public static boolean permitirSiguiente=false;
    public static final int PORT = 3400;
    public static final String SERVER = "localhost";  
    public static boolean IteradorInfinito;
    private Socket clientSideSocket;
    
    private String fromServer;
    
    
    public void protocol (Socket socket) throws Exception {
    		createStreams(socket);
    		
    		
             if(permitirSiguiente) {
            	  toNetwork.println(command);

             
             fromServer = fromNetwork.readLine();
             
             }
            	 permitirSiguiente=false;
             
             

    }
    private static void createStreams (Socket socket) throws IOException {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    
    
    
    
    
    public void init() {
    	 try {
				clientSideSocket =new Socket (SERVER, PORT);
				protocol(clientSideSocket);
				clientSideSocket.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	        
	    	}
    
    
    
    
    @FXML
    void initialize()  {
    	
    	 DropShadow effect = new DropShadow();
 	    effect.setBlurType(BlurType.GAUSSIAN);
 	    effect.setColor(Color.rgb(0,0,0, .5));
 	    effect.setSpread(0);
 	    effect.setOffsetX(0);
 	    effect.setOffsetY(7);
 	    
 	    btnSalir.setEffect(effect);
 	    btnContinuar.setEffect(effect);
 	    
 	   btnSalir.setOnMouseClicked(e -> {
   		System.exit(0);
 	   });
 	   
 	   btnContinuar.setOnMouseClicked(e -> {
 	   		
 		   command = readMenu(opcion+"");
 		   command+= " (" + new Date() + ")";
 		   System.out.println(command);
 		  Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/interfaces/Cliente.fxml"));
			Scene scene = new Scene(root);
 	        ClientApp.stage.setScene(scene);
 	       ClientApp.stage.hide();
 	        
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		permitirSiguiente = true;
		
		init();
		
		Transaccion t = new Transaccion(command, new Date());
		BancoServer.getTransacciones().add(t);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/interfaces/Notificacion.fxml"));
		try {
			root = loader.load();
			Scene scene = new Scene(root);
	        ControladorNotificacion controllerNoti = loader.getController();
	        controllerNoti.setTextNotificacion(fromServer);
	        controllerNoti.inicializarNotificacion();
	        Stage primaryStage = new Stage();
	        primaryStage.initStyle(StageStyle.UNDECORATED);
	        primaryStage.setScene(scene);
	        primaryStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		
 	        
 	   });
    	

    }
    
    
    
    
    
    public void iniciarAbrirCuenta() {
    	tituloOperacionLabel.setText("Abrir nueva cuenta de ahorros");
    	operacion1Label.setText("Nombre de usuario: ");
    	operacion2Label.setVisible(false);
    	operacion2Text.setVisible(false);
    	opcion=1;
    	
    }
    
    public void iniciarAbrirBolsillo() {
    	tituloOperacionLabel.setText("Abrir nuevo bolsillo");
    	operacion1Label.setText("Número de cuenta: ");
    	operacion2Label.setVisible(false);
    	operacion2Text.setVisible(false);
    	opcion=2;
    }
    
    
    public void iniciarCancelarBolsillo() {
    	tituloOperacionLabel.setText("Cancelar bolsillo existente");
    	operacion1Label.setText("Número de cuenta del bolsillo: ");
    	operacion2Label.setVisible(false);
    	operacion2Text.setVisible(false);
    	opcion=3;
    }
    
    public void iniciarCancelarCuenta() {
    	tituloOperacionLabel.setText("Cancelar cuenta de ahorros");
    	operacion1Label.setText("Número de cuenta: ");
    	operacion2Label.setVisible(false);
    	operacion2Text.setVisible(false);
    	opcion=4;
    }
    
    
    public void iniciarDepositar(){
    	tituloOperacionLabel.setText("Nuevo deposito");
    	operacion1Label.setText("Número de cuenta: ");
    	operacion2Label.setText("Saldo a depositar: ");
    	opcion=5;
    }
    
    public void iniciarRetirar() {
    	tituloOperacionLabel.setText("Nuevo retiro");
    	operacion1Label.setText("Número de cuenta: ");
    	operacion2Label.setText("Saldo a retirar: ");
    	opcion=6;
    }
    
    public void iniciarTrasladar() {
    	tituloOperacionLabel.setText("Traslado de saldo al bolsillo");
    	operacion1Label.setText("Número de cuenta: ");
    	operacion2Label.setText("Saldo a trasladar: ");
    	opcion=7;
    }
    
    public void iniciarConsultar() {
    	tituloOperacionLabel.setText("Consultar saldo");
    	operacion1Label.setText("Numero de cuenta: ");
    	operacion2Label.setVisible(false);
    	operacion2Text.setVisible(false);
    	opcion=8;
    }
    
    
    public String readMenu(String option) {
    	int opt = Integer.parseInt(option);
    	String numCuenta = "";
    	String command ="";
    	String saldo = "";
    	switch (opt) {
		case 1: 
				String usuario = operacion1Text.getText();
				command = "ABRIR_CUENTA " + usuario;
			break;
			
		case 2: 
        		numCuenta = operacion1Text.getText();
        		if(BancoServer.isNumber(numCuenta))
				command = "ABRIR_BOLSILLO " + numCuenta	;
        		else command = "ERROR_CUENTA ";
			break;
			
		case 3:
				numCuenta = operacion1Text.getText();
				command = "CANCELAR_BOLSILLO " + numCuenta;
			break;
			
		case 4:
				numCuenta = operacion1Text.getText();
				if(BancoServer.isNumber(numCuenta))
				command = "CANCELAR_CUENTA " + numCuenta;
				else command = "ERROR_CUENTA ";
			break;
			
		case 5: 
		        numCuenta = operacion1Text.getText();
				saldo = operacion2Text.getText();
				if(BancoServer.isNumber(numCuenta)) {
					if(BancoServer.isNumber(saldo))
						command = "DEPOSITAR " + numCuenta + " " + saldo;
					else command = "ERROR_SALDO ";
				} else command = "ERROR_CUENTA ";
			break;
			
		case 6: 
        		numCuenta = operacion1Text.getText();
				saldo = operacion2Text.getText(); 
				if(BancoServer.isNumber(numCuenta)) {
					if(BancoServer.isNumber(saldo))
						command = "RETIRAR " + numCuenta + " " + saldo;
					else command = "ERROR_SALDO ";
				} else command = "ERROR_CUENTA ";
				
			break;
			
		case 7: 
				numCuenta = operacion1Text.getText();
				saldo = operacion2Text.getText();
				if(BancoServer.isNumber(numCuenta)) {
					if(BancoServer.isNumber(saldo))
						command = "TRASLADAR " + numCuenta + " " + saldo;
					else command = "ERROR_SALDO ";
				} else command = "ERROR_CUENTA ";
				
			break;
			
		case 8: 
				numCuenta = operacion1Text.getText();
				command = "CONSULTAR " + numCuenta;
			break;
		

		}
    	
    	return command;
    }
}
