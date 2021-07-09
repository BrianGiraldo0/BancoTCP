package co.edu.uniquindio.banco;

import java.util.Date;

public class Transaccion {
	private String comandoTransaccion;
	private Date fecha;
	public String getComandoTransaccion() {
		return comandoTransaccion;
	}
	public void setComandoTransaccion(String comandoTransaccion) {
		this.comandoTransaccion = comandoTransaccion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Transaccion(String comandoTransaccion, Date fecha) {
		super();
		this.comandoTransaccion = comandoTransaccion;
		this.fecha = fecha;
	}
	
	
}
