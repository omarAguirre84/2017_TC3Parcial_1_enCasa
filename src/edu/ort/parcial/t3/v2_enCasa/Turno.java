package edu.ort.parcial.t3.v2_enCasa;

import edu.ort.parcial.t3.v2_enCasa.ObraSocialEnum;

public class Turno {
	private String nombrePaciente;
	private ObraSocialEnum obraSocial;
	private long numero;

	public Turno(Paciente paciente) {
		this.nombrePaciente = paciente.getNombre();
		this.obraSocial = paciente.getObraSocial();
		this.numero = paciente.getNumero();
	}

	public String getNombre() {
		return nombrePaciente;
	}

	public void setNombre(String nombre) {
		this.nombrePaciente = nombre;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public ObraSocialEnum getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocialEnum obraSocial) {
		this.obraSocial = obraSocial;
	}

	@Override
	public String toString() {
		return "Turno [nombrePaciente=" + nombrePaciente + ", obraSocial=" + obraSocial + ", numero=" + numero + "]";
	}
}
