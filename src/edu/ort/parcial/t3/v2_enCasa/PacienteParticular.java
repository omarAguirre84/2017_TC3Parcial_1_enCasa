package edu.ort.parcial.t3.v2_enCasa;

import java.security.InvalidParameterException;

public class PacienteParticular extends Paciente{
	final static long MIN_NRO_DNI = 750000;
	
	public PacienteParticular(String nombre, long nroDNI) throws InvalidParameterException {
		super(nombre, ObraSocialEnum.PARTICULAR, nroDNI);
	}

	@Override
	protected long getValorMinimo() {
		return this.MIN_NRO_DNI;
	}

}
