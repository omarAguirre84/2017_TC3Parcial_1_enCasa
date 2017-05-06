package edu.ort.parcial.t3.v2_enCasa;

public abstract class Paciente {
	private String nombre;
	private ObraSocialEnum obraSocial;
	private long nro;

	public Paciente(String nombre, ObraSocialEnum obraSocial, long nro) throws IllegalArgumentException{
		if(nombre == "null" || !nroValido(nro)){
			throw new IllegalArgumentException("Datos de paciente invalidos");
		}
		this.nombre = nombre;
		this.obraSocial = obraSocial;
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
}
