package edu.ort.parcial.t3.v2_enCasa;

public abstract class Paciente {
	private String nombre;
	private ObraSocialEnum obraSocial;
	private long nro;

	protected Paciente(String nombre, ObraSocialEnum os, long nro) {
		this.nombre = nombre;
		this.obraSocial = os;
		this.nro = nro;
	}

	public String getNombre() {
		return nombre;
	}

	public ObraSocialEnum getObraSocial() {
		return this.obraSocial;
	}

	protected boolean nroValido(long nro) {
		return nro >= getValorMinimo();
	}

	protected long getNumero() {
		return this.nro;
	}

	protected abstract long getValorMinimo();

	@Override
	public String toString() {
		return "Paciente [nombre=" + nombre + ", obraSocial=" + obraSocial + ", nro=" + nro + "]";
	}
	
}
