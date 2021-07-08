package co.edu.uniquindio.banco;

public class Cuenta {
	
	private int numCuenta;
	private String nombreUsuario;
	private double saldo;
	private Bolsillo bolsillo;
	
	
	public Cuenta(int numCuenta, String nombreUsuario, double saldo) {
		this.numCuenta = numCuenta;
		this.nombreUsuario = nombreUsuario;
		this.saldo = saldo;
	}
	
	public Cuenta(String nombreUsuario, double saldo) {
		this.nombreUsuario = nombreUsuario;
		this.saldo = saldo;
	}
	
	public int getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(int numCuenta) {
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

	public Bolsillo getBolsillo() {
		return bolsillo;
	}

	public void setBolsillo(Bolsillo bolsillo) {
		this.bolsillo = bolsillo;
	}
	
}
