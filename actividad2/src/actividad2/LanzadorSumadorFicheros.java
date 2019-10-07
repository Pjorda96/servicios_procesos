package actividad2;

import java.io.*;

public class LanzadorSumadorFicheros {

	private void lanzarSumadorFichero(String carpeta) {
		ProcessBuilder pb;
		try {
			pb = new ProcessBuilder("java", "-cp", "bin", "actividad2.SumadorFichero", carpeta);
			pb.redirectError(new File("error_SumadorFichero.txt"));
			pb.redirectOutput(new File("salida_SumadorFichero.txt"));
			pb.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void ejercicio3() throws InterruptedException {
		File file1 = new File("sumadorFichero.lock");

		this.lanzarSumadorFichero("\\carpeta");

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
		LanzadorSumadorFicheros l = new LanzadorSumadorFicheros();
		l.ejercicio3();
	}
}
