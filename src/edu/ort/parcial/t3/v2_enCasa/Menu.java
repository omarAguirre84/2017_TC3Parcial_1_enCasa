package edu.ort.parcial.t3.v2_enCasa;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private Scanner input;
	private String titulo;
	private ArrayList<String> opciones;

	public Menu(String titulo, String[] opciones, Scanner input) {
		this.titulo = titulo;
		this.opciones = new ArrayList<String>();
		for (int o = 0; o < opciones.length; o++) {
			this.opciones.add(opciones[o]);
		}
		this.input = input;
	}

	public int pedirOpcion() {
		System.out.println(titulo);

		System.out.println();
		System.out.println("Ingrese opcion:");
		for (int i = 0; i < opciones.size(); i++) {
			System.out.println((i + 1) + ": " + opciones.get(i));
		}
		System.out.println();
		System.out.print(">> ");

		int opcion;
		try {
			opcion = Integer.parseInt(input.nextLine());
		} catch(NumberFormatException nfe) {
			opcion = 0;
		}
		while (opcion < 1 || opcion > opciones.size()) {
			System.out.println("Error: la opcion debe estar entre 1 y " + opciones.size());
			try {
				opcion = Integer.parseInt(input.nextLine());
			} catch(NumberFormatException nfe) {
				opcion = 0;
			}
		}
		return opcion;
	}

	public int opcionFin() {
		return opciones.size();
	}

}
