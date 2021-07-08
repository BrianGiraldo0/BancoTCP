package co.edu.uniquindio.banco;

public class Bolsillo {
	
	private String numCuenta;
	private String nombreUsuario;
	private double saldo;
	
	
	public Bolsillo(String numCuenta, String nombreUsuario, double saldo) {
		this.numCuenta = numCuenta;
		this.nombreUsuario = nombreUsuario;
		this.saldo = saldo;
	}
	
	
	public String getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public String toString() {
		return numCuenta + ", " + nombreUsuario + ", " + saldo;
	}
	

}
