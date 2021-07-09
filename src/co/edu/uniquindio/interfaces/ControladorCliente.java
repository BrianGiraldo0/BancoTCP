package co.edu.uniquindio.interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.banco.ClientApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class ControladorCliente{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAbrirCuenta;

    @FXML
    private Button btnAbrirBolsillo;

    @FXML
    private Button btnCancelarBolsillo;

    @FXML
    private Button btnCancelarCuenta;

    @FXML
    private Button btnDepositar;

    @FXML
    private Button btnRetirar;

    @FXML
    private Button btnTrasladar;

    @FXML
    private Button btnConsultar;
    
    @FXML
    private Button btnSalir;

    private ControladorFormulario controller;



    
    @FXML
    void initialize() throws IOException {
    	 DropShadow effect = new DropShadow();
    	    effect.setBlurType(BlurType.GAUSSIAN);
    	    effect.setColor(Color.rgb(0,0,0, .5));
    	    effect.setSpread(0);
    	    effect.setOffsetX(0);
    	    effect.setOffsetY(7);
    	    iniciarBotones();
    	    btnAbrirCuenta.setEffect(effect);
    	    btnAbrirBolsillo.setEffect(effect);
    	    btnCancelarBolsillo.setEffect(effect);
    	    btnCancelarCuenta.setEffect(effect);
    	    btnConsultar.setEffect(effect);
    	    btnDepositar.setEffect(effect);
    	    btnRetirar.setEffect(effect);
    	    btnTrasladar.setEffect(effect);
    	    btnSalir.setEffect(effect);
    	    
    	    
    }
    
    public void iniciarBotones() throws IOException {
    	
    	btnAbrirCuenta.setOnMouseClicked(e -> {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/interfaces/Formulario.fxml"));
    		try {
				Parent root = loader.load();
				controller = loader.getController();
	    		controller.iniciarAbrirCuenta();
	    		
	    		Scene scene = new Scene(root);
	    	    ClientApp.stage.setScene(scene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	});
    	
    	btnAbrirBolsillo.setOnMouseClicked(e -> {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/interfaces/Formulario.fxml"));
    		try {
				Parent root = loader.load();
				controller = loader.getController();
				controller.iniciarAbrirBolsillo();
	    		Scene scene = new Scene(root);
	    	    ClientApp.stage.setScene(scene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	});
    	
    	btnCancelarBolsillo.setOnMouseClicked(e -> {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/interfaces/Formulario.fxml"));
    		try {
				Parent root = loader.load();
				controller = loader.getController();
				controller.iniciarCancelarBolsillo();
	    		Scene scene = new Scene(root);
	    	    ClientApp.stage.setScene(scene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	});
    	
    	btnCancelarCuenta.setOnMouseClicked(e -> {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/interfaces/Formulario.fxml"));
    		try {
				Parent root = loader.load();
				controller = loader.getController();
				controller.iniciarCancelarCuenta();
	    		Scene scene = new Scene(root);
	    	    ClientApp.stage.setScene(scene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	});
    	
    	btnDepositar.setOnMouseClicked(e -> {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/interfaces/Formulario.fxml"));
    		try {
				Parent root = loader.load();
				controller = loader.getController();
				controller.iniciarDepositar();
	    		Scene scene = new Scene(root);
	    	    ClientApp.stage.setScene(scene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	});
    	
    	btnRetirar.setOnMouseClicked(e -> {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/interfaces/Formulario.fxml"));
    		try {
				Parent root = loader.load();
				controller = loader.getController();
				controller.iniciarRetirar();
	    		Scene scene = new Scene(root);
	    	    ClientApp.stage.setScene(scene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	});
    	
    	btnTrasladar.setOnMouseClicked(e -> {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/interfaces/Formulario.fxml"));
    		try {
				Parent root = loader.load();
				controller = loader.getController();
				controller.iniciarTrasladar();
	    		Scene scene = new Scene(root);
	    	    ClientApp.stage.setScene(scene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	});
    	
    	btnConsultar.setOnMouseClicked(e -> {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/interfaces/Formulario.fxml"));
    		try {
				Parent root = loader.load();
				controller = loader.getController();
				controller.iniciarConsultar();
	    		Scene scene = new Scene(root);
	    	    ClientApp.stage.setScene(scene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	});
    	
    	btnSalir.setOnMouseClicked(e -> {
    		System.exit(0);
    	});
    	
		
    }
    
    
    
}
