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
		this.horaInicio = horaInicio;
		this.turnos = new Turno[especialidades][horaFin-horaInicio];
//		System.out.println(especialidades);
//		System.out.println(horaFin-horaInicio);
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
			System.out.println("si");
			
		}else{
			System.out.println("no");
			throw new TurnoOcupadoException();
		}
		
		// completar
	}
}
