package actividad2;

import java.io.*;

public class LanzadorCuentavocales {
	
	private void lanzarCuentavocales() {
		ProcessBuilder pb;
		try {
			pb = new ProcessBuilder("java", "-cp", "bin", "actividad2.Cuentavocales");
			pb.redirectError(new File("error_cuentavocales.txt"));
			pb.redirectOutput(new File("salida_cuentavocales.txt"));
			pb.start();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void ejercicio2() throws InterruptedException {
		File file1 = new File("cuentavocales.lock");

		this.lanzarCuentavocales();
		
		while(file1.isFile()) {
			System.out.println("Esperando a que terminen los procesos");
			Thread.sleep(200);
		}
		System.out.println("Procesos terminados.");
	}

	/**
	 * @param args null
	 * @throws InterruptedException e
	 */
	public static void main(String[] args) throws InterruptedException {
		LanzadorCuentavocales l = new LanzadorCuentavocales();
		l.ejercicio2();
	}
}
