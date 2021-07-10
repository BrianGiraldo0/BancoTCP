package co.edu.uniquindio.banco;

import co.edu.uniquindio.interfaces.ControladorCliente;
import co.edu.uniquindio.server.BancoServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClientApp extends Application{
	
	public static ControladorCliente controller;
	public static Stage stage;
    public static void main(String args[]) throws Exception {
    	
    	
    	Thread hiloServer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				BancoServer banco = new BancoServer();
				try {
					banco.init();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
    	hiloServer.start();
    	launch(args);
    }
    
    
    
    @Override
	public void start(Stage primaryStage) throws Exception {
    	stage = primaryStage;
    	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/interfaces/Cliente.fxml"));
		Parent root = loader.load();
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color: #FFFFFF");
        controller = loader.getController();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
}
