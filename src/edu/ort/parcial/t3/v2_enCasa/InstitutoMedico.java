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
		String[] opciones = { "Turnos de Especialidad", "Turnos Asignados por Horario", "Reservar Turno", "Mostrar Reportes", "Salir" };
		// completar
		this.input = new Scanner(System.in);
		
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.menu = new Menu("INSTITUTO PEPE", opciones, input); 
		this.agendaDeHoy = new AgendaHoraria(EspecialidadEnum.values().length, horaInicio, horaFin);
	}

	public void iniciarActividadDiaria() {
		int opcion = menu.pedirOpcion();
		
		while (opcion != menu.opcionFin()) {
			switch (opcion) {
			case 1:
				mostrarTurnosPorEspecialidad();
				break;
			case 2:
				mostrarTurnosAsignadosPorHora();
				break;
			case 3:
				try {
					agregarTurnoDiario();
				} catch (TurnoOcupadoException e) {
					System.out.println("El turno solicitado ya esta ocupado");
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
			opcion = menu.pedirOpcion();
		}
		System.out.println("\nFin del programa");
		input.close();
	}

	/*
	 * Opciones de Menu
	 */
	// OPCION 1
	public void mostrarTurnosPorEspecialidad() {
		// esta opcion debe controlar una excepcion
		// reemplazar los null por lo que corresponda en cada caso
			
		EspecialidadEnum especialidad = pedirEspecialidad(); // <- reemplazar
		int indiceEspecialidad = especialidad.ordinal();
		
		Turno[][] agendaDiaria = agendaDeHoy.getTurnos(); // <- reemplazar
		
//		System.out.println("especialidades "+agendaDiaria.length);
//		System.out.println("horas "+agendaDiaria[0].length);
		int hora = this.horaInicio;
		System.out.println("TURNOS "+EspecialidadEnum.values()[indiceEspecialidad].getDescripcion());
		
		for (int i = 0; i < agendaDiaria[indiceEspecialidad].length; i++) {
			if (agendaDiaria[indiceEspecialidad][i] != null) {
				System.out.println(hora+"hs : "+agendaDiaria[indiceEspecialidad][i].getNombre());
			}else{
				System.out.println(hora+"hs : "+"No hay pacientes");
			}
			hora++;
		}
		// completar
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
		
		this.agendaDeHoy.asignarTurno(pedirEspecialidad().ordinal(), pedirHoraTurno(), crearPaciente());
		
	}

	public Paciente crearPaciente() {
		Paciente paciente;
		
		String nombre="";
	
		ObraSocialEnum os = pedirObraSocial();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Ingrese el apellido y nombre del paciente");
		try {
			nombre = scan.nextLine();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		if (os == ObraSocialEnum.PARTICULAR) {
			System.out.println("Ingrese el DNI del paciente");
			long nro = Long.parseLong(scan.nextLine());
			paciente = new PacienteParticular(nombre, nro); // <- reemplazar
		} else {
			System.out.println("Ingrese el nro de socio del paciente en la Obra Social");
			long nro = Long.parseLong(scan.nextLine());
			paciente = new PacienteOS(nombre, os, nro);  // <- reemplazar
		}
		scan.close();
		return paciente;
	}

	// OPCION 4
	public void reportePorcentajeAsignacion() {
		System.out.println("Porcentaje de asignaci�n");
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
			// Completar. Puede haber m�s de un elemento que cumpla la condici�n deseada.
		}
		if (minimoAsignacion == 0) {
			System.out.println("Los siguientes horarios no tienen turnos asignados");
		} else {
			System.out.println(
					"Los siguientes horarios tienen el m�nimo de turnos asignados (" + minimoAsignacion + ").");
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
		ObraSocialEnum res = null;
		Scanner in = new Scanner(System.in);
		boolean done = false;
		int opc=0;
		
		System.out.println("OBRAS SOCIALES: ");
		
		for (int i = 0; i < ObraSocialEnum.values().length; i++) {
			System.out.println(i+1+":"+ObraSocialEnum.values()[i]);
		}
		System.out.println("Elija una opcion: ");
		do {
			try {
				opc = Integer.parseInt(in.nextLine());
				
				if (opc > 0 && opc < ObraSocialEnum.values().length+1) {
					done = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("try again");
			}
		} while (!done);
		
		res = ObraSocialEnum.values()[opc-1];
		
		in.close();
		return res;
	}

	private int pedirHoraTurno() {
		int nro = -1;
		Scanner in = new Scanner(System.in);
		boolean done=false;
		do {
			// completar
			try {
				System.out.print("Hora del turno?: ");
				nro = Integer.parseInt(input.nextLine());
				if (nro >= this.horaInicio && nro <= this.horaFin) {
					done = true;
					in.close();
				}
			} catch (NumberFormatException e) {
				System.out.println("Ingrese hora entre las "+this.horaInicio + " y las "+this.horaFin);
				System.out.print("Hora del turno?: ");
				nro = input.nextInt();
			}
		} while ((nro < this.horaInicio || nro > this.horaFin) && !done);
		
		return nro;
	}
}









