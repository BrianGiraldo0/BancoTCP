package co.edu.uniquindio.banco;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApp extends Application{
	
	public static Stage stage;
    public static void main(String args[]) {
    	launch(args);
    }
    
    @Override
	public void start(Stage primaryStage) throws Exception {
    	stage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/interfaces/Cliente.fxml"));
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color: #FFFFFF");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
}
