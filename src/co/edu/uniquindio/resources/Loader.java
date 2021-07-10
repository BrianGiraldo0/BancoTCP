package co.edu.uniquindio.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {

	/**
	 * Método que sirve para cargar las transacciones y guardarlas en una lista 
	 * @param URL nombre del archivo txt
	 * @return
	 */
	public static ArrayList<String> cargarTransacciones(String URL){
		ArrayList<String> lista = new ArrayList<>();
		 try {
		      File myObj = new File(URL);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		       lista.add(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		return lista;
	}
}
