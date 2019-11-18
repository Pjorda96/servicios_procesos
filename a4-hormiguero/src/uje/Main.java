package uje;

import java.util.ArrayList;

public class Main {
	final static int cuidadoras = 3 * 3; //3 cuidadoras por 3 tipos de huevos
	final static int obreras = 2;

	public static void main(String[] args) throws InterruptedException {
		// Creamos una lista de hilos para lanzarlos posteriormente.
		ArrayList<Thread> hilos = new ArrayList<Thread>();

		Hormiguero h = new Hormiguero();
		Thread hiloHormiguero = new Thread(h);
		hilos.add(hiloHormiguero);
		
		Reina r = new Reina();		
		Thread hiloReina = new Thread(r);
		hilos.add(hiloReina);
		
		for(int i = 0; i < cuidadoras; i++) {			
			Cuidadora c = new Cuidadora();
			Thread hiloCuidadora = new Thread(c);			
			hilos.add(hiloCuidadora);
		}
		
		for(int i = 0; i < obreras; i++) {			
			Obrera o = new Obrera();
			Thread hiloObrera = new Thread(o);			
			hilos.add(hiloObrera);
		}	
		
		// La longitud de el bucle se basa en el numero de hilos que hemos lanzado.
		for(int i = 0; i < cuidadoras + obreras + 2; i++) {
			hilos.get(i).start();
		}
		
		// La longitud de el bucle se basa en el numero de hilos que hemos lanzado.
		for(int i = 0; i < cuidadoras + obreras + 2; i++) {
			hilos.get(i).join();
		}
	}

}
