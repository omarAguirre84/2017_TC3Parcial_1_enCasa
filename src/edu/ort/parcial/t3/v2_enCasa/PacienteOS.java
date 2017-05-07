package edu.ort.parcial.t3.v2_enCasa;

public class PacienteOS extends Paciente {

	final static long MIN_NRO_OS = 1;

	public PacienteOS(String nombre, ObraSocialEnum os, long nro) {
		super(nombre, os, nro);
	}

	@Override
	protected long getValorMinimo() {
		return this.MIN_NRO_OS;
	}
	
}
