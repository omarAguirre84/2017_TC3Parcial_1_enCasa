package edu.ort.parcial.t3.v2_enCasa;

public class AgendaHoraria {
	private Turno turnos[][];
	private int horaInicio;

	/**
	 * @param especialidades
	 * @param horaInicio
	 * @param horaFin
	 */
	public AgendaHoraria(int especialidades, int horaInicio, int horaFin) {
		// implementar
	}

	public Turno[][] getTurnos() {
		return this.turnos.clone();
	}

	public Turno getTurno(int especialidad, int hora) {
		return turnos[especialidad][hora - this.horaInicio];
	}
		
	public void asignarTurno(int especialidad, int hora, Paciente paciente) throws TurnoOcupadoException {
		if (turnos[especialidad][hora - this.horaInicio] == null) {
			turnos[especialidad][hora - this.horaInicio] = new Turno(paciente);
		}
		// completar
	}
}
