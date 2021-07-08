package co.edu.uniquindio.interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.banco.ClientApp;
import co.edu.uniquindio.cliente.BancoClient;
import co.edu.uniquindio.cliente.BancoClientProtocol;
import co.edu.uniquindio.server.BancoServer;
import javafx.application.Application;
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

public class ControladorFormulario {

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
    
    private int opcion;
    
    @FXML
    void initialize() {
        
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
 	   		
 		   String command = readMenu(opcion+"");
 		   System.out.println(command);
 		  Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/interfaces/Cliente.fxml"));
			Scene scene = new Scene(root);
 	        ClientApp.stage.setScene(scene);
 	        
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
    	operacion2Label.setText("Saldo a depositar: ");
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
		
		case 9: BancoClient.IteradorInfinito = false;
				command= "SALIR";

		}
    	
    	return command;
    }
}
