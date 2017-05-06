package edu.ort.parcial.t3.v2_enCasa;

import java.util.ArrayList;
import java.util.Scanner;

public class InstitutoMedico {

	private int horaInicio;
	private int horaFin;

	private AgendaHoraria agendaDeHoy;

	private Scanner input;
	private Menu menu;

	public InstitutoMedico(int horaInicio, int horaFin) {
		String[] opciones = { "Turnos de Especialidad", "Turnos Asignados por Horario", "Reservar Turno",
				"Mostrar Reportes", "Salir" };
		// completar
		this.input = new Scanner(System.in);
		this.menu = new Menu("Instituto PEPE, su salud en las mejores manos", opciones, input);
	}

	public void iniciarActividadDiaria() {
		int opcion = menu.pedirOpcion();
		while (opcion != menu.opcionFin()) {
			switch (opcion) {
			case 1:
				try {
					mostrarTurnosPorEspecialidad();
				} catch (IllegalArgumentException e) {
					System.err.println("Ingrese datos validos");
					opcion = menu.pedirOpcion();
				} catch (NullPointerException e) {
					System.err.println("No hay turnos cargados");
					opcion = menu.pedirOpcion();
				}
				break;
			case 2:
				mostrarTurnosAsignadosPorHora();
				break;
			case 3:
				try {
					agregarTurnoDiario();
				} catch (TurnoOcupadoException e) {
					System.out.println("El turno solicitado ya está ocupado");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				reportePorcentajeAsignacion();
				reporteHoraMenosSolicitada();
				break;
			}
			System.out.println();
//			opcion = menu.pedirOpcion(); //GENERA UN ENTER DE MAS
		}
		System.out.println("\nFin del programa");
		input.close();
	}

	/*
	 * Opciones de Menu
	 */
	// OPCION 1
	public void mostrarTurnosPorEspecialidad() throws NullPointerException, IllegalArgumentException{
		// esta opción debe controlar una excepción
		try {
			EspecialidadEnum especialidad = this.pedirEspecialidad();
			int indiceEspecialidad = especialidad.ordinal();
		} catch (IllegalArgumentException e) {
			throw e;
		}
		Turno[][] agendaDiaria = this.agendaDeHoy.getTurnos(); 
		if (agendaDiaria == null) {
			throw new NullPointerException();
		}

	}

	// OPCION 2
	public void mostrarTurnosAsignadosPorHora() {
		int cantidadTurnos;
		Turno[][] agendaDiaria = agendaDeHoy.getTurnos();
		Turno turno = null;
		System.out.println("Listado de turnos asignados por hora");
		for (int j = 0; j < agendaDiaria[0].length; j++) {
			System.out.println((j + horaInicio) + " hs.");
			cantidadTurnos = 0;
			for (int i = 0; i < agendaDiaria.length; i++) {
				if (agendaDiaria[i][j] != null) {
					turno = agendaDiaria[i][j];
					System.out.println(String.format("%s - %s", EspecialidadEnum.values()[i].getDescripcion(), turno));
					cantidadTurnos++;
				}
			}
			if (cantidadTurnos == 0) {
				System.out.println("NO HAY PACIENTES REGISTRADOS");
			}
		}
	}

	// OPCION 3
	private void agregarTurnoDiario() throws TurnoOcupadoException, Exception {
		// implementar
	}

	private Paciente crearPaciente() {
		Paciente paciente;
		ObraSocialEnum os = pedirObraSocial();
		System.out.println("Ingrese el apellido y nombre del paciente");
		String nombre = input.nextLine();
		if (os == ObraSocialEnum.PARTICULAR) {
			System.out.println("Ingrese el DNI del paciente");
			long nro = Long.parseLong(input.nextLine());
			paciente = null; // <- reemplazar
		} else {
			System.out.println("Ingrese el nro de socio del paciente en la Obra Social");
			long nro = Long.parseLong(input.nextLine());
			paciente = null; // <- reemplazar
		}
		return paciente;
	}

	// OPCION 4
	public void reportePorcentajeAsignacion() {
		System.out.println("Porcentaje de asignación");
		for (EspecialidadEnum especialidad : EspecialidadEnum.values()) {
			System.out.println(especialidad.toString() + ": " + porcentajeAsignacion(especialidad) + "% de asignacion");
		}
	}

	public double porcentajeAsignacion(EspecialidadEnum especialidad) {
		int ordinal = especialidad.ordinal();
		int contador = 0;
		Turno[][] agendaDiaria = agendaDeHoy.getTurnos();
		int totalTurnos = agendaDiaria[ordinal].length;
		double porcentaje = 0.0;

		for (int i = 0; i < totalTurnos; i++) {
			if (agendaDiaria[ordinal][i] != null) {
				contador++;
			}
		}
		porcentaje = 100 * contador / totalTurnos;
		return porcentaje;
	}

	private void reporteHoraMenosSolicitada() {
		int minimoAsignacion = 0;
		int aux = 0;
		ArrayList<Integer> horarios = new ArrayList<Integer>();

		for (int j = horaInicio; j < horaFin; j++) {
			aux = cantidadAsignados(j);
			// Completar. Puede haber más de un elemento que cumpla la condición
			// deseada.
		}
		if (minimoAsignacion == 0) {
			System.out.println("Los siguientes horarios no tienen turnos asignados");
		} else {
			System.out.println(
					"Los siguientes horarios tienen el mínimo de turnos asignados (" + minimoAsignacion + ").");
		}
		for (Integer i : horarios) {
			System.out.println(i + "hs.");
		}
	}

	private int cantidadAsignados(int hora) {
		int cantidad = 0;
		int indiceHoras = hora - horaInicio;
		Turno[][] agendaDiaria = agendaDeHoy.getTurnos();
		for (int i = 0; i < agendaDiaria.length; i++) {
			if (agendaDiaria[i][indiceHoras] != null) {
				cantidad++;
			}
		}

		return cantidad;
	}

	// Carga de Datos
	private EspecialidadEnum pedirEspecialidad() throws IllegalArgumentException {
		System.out.println("ESPECIALIDADES");
		for (int es = 0; es < EspecialidadEnum.values().length; es++) {
			System.out.println(EspecialidadEnum.values()[es] + " - " + EspecialidadEnum.values()[es].getDescripcion());
		}
		System.out.println("Ingrese una especialidad");
		String especialidad = input.nextLine().toUpperCase();
		EspecialidadEnum value = null;
		try {
			value = EspecialidadEnum.valueOf(especialidad);
		} catch (IllegalArgumentException iae) {
			throw new IllegalArgumentException(
					String.format("La Especialidad ingresada ('%s') no existe", especialidad));
		}
		return value;
	}

	private ObraSocialEnum pedirObraSocial() throws IllegalArgumentException {
		// completar
		return null;
	}

	private int pedirHoraTurno() {
		int nro = -1;
		do {
			// completar
		} while (nro < horaInicio || nro > horaFin);
		return nro;
	}
}
