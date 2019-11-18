package uje;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Reina implements Runnable {
	
	public static BlockingQueue<String> obrera;
	public static BlockingQueue<String> cuidadora;
	public static BlockingQueue<String> guerrera;
	
	Reina() { 
		obrera = new LinkedBlockingDeque<String>();
		cuidadora = new LinkedBlockingDeque<String>();
		guerrera = new LinkedBlockingDeque<String>();
	}

	@Override
	public void run() {		
		while (true) {
			try {
				ponerHuevo();
				incubarHuevo();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}				
	}
	
	private void ponerHuevo() throws InterruptedException {
		Random rnd = new Random();
		int Numero = rnd.nextInt(100);
		
		if (Numero > 66) {
			obrera.put("huevo obrera");
		} else if (Numero > 33) {
			cuidadora.put("huevo cuidadora");
		} else {
			guerrera.put("huevo guerrera");
		}
	}
	
	public void incubarHuevo() throws InterruptedException {
		Thread.sleep((long) Math.random()*(500-100)+100);
	}
	
	public static String cogerHuevoParent() throws InterruptedException {
		Random rnd = new Random();
		int numero = rnd.nextInt(100);
		
		String colaCuidadora;
		
		try {
			/*
			 * Asignamos de que cola debe coger el huevo la cuidadora, dandole el 33% a cada cola.
			 */
			if (numero > 66) {
				obrera.take();
				colaCuidadora = "obrera";
			} else if (numero > 33) {
				cuidadora.take();
				colaCuidadora = "cuidadora";
			} else {
				guerrera.take();
				colaCuidadora = "guerrera";
			}
			return colaCuidadora;
		} catch (InterruptedException e) {				
			e.printStackTrace();
		}
		return null;
	}
		
}
