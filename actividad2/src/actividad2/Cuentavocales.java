package actividad2;

import java.io.*;

public class Cuentavocales {

	/**
	 *
	 * @param line String
	 */
	private int contador(String line) {
		int contador = 0;
		for(int x=0; x<line.length(); x++) {
			if ((Character.toLowerCase(line.charAt(x))=='a') || (Character.toLowerCase(line.charAt(x))=='e') || (Character.toLowerCase(line.charAt(x))=='i') || (Character.toLowerCase(line.charAt(x))=='o') || (Character.toLowerCase(line.charAt(x))=='u')){
				contador++;
			}
		}
		return contador;
	}

	/**
	 * @param args null
	 * @throws IOException e
	 */
	public static void main(String[] args) throws IOException {
		Cuentavocales cuentavocales = new Cuentavocales();
		int contador = 0;

		BufferedReader reader = new BufferedReader(new FileReader("cuentavocales.txt"));
		String currentLine;
		while((currentLine = reader.readLine()) != null) {
			contador += cuentavocales.contador(currentLine);
		}
		reader.close();

		FileWriter fileWriter = new FileWriter("salida_cuentavocales.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(contador);
		printWriter.close();
		File lockFile = new File("cuentavocales.lock");
	}
}
