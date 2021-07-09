package co.edu.uniquindio.interfaces;

import co.edu.uniquindio.banco.ClientApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControladorNotificacion {

    @FXML
    private Label lblNotificacion;
    
    private String textNotificacion;
    
    @FXML
    private Button btnOK;
    
    
    public void inicializarNotificacion() {
    	lblNotificacion.setText(textNotificacion);
    	lblNotificacion.setWrapText(true);
    	if(textNotificacion.split(" ")[0].equals("Error:"))
    		lblNotificacion.setTextFill(Color.web("#D85D5D"));
    	btnOK.setOnMouseClicked(e -> {
    		
			Stage stage = (Stage) btnOK.getScene().getWindow();
		    // do what you have to do
		    stage.close();
		    
		    ClientApp.stage.show();
    	});
    }


	public String getTextNotificacion() {
		return textNotificacion;
	}


	public void setTextNotificacion(String textNotificacion) {
		this.textNotificacion = textNotificacion;
	}
    
    
    

}
