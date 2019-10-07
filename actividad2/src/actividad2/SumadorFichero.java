package actividad2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.NClob;
import java.util.Objects;

public class SumadorFichero {

	/**
	 *
	 * @param file file
	 * @return int
	 * @throws IOException e
	 */
	private int sumador(String file) throws IOException {
		int contador = 0;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		while((currentLine = reader.readLine()) != null) {
			contador += Integer.parseInt(currentLine);
		}
		reader.close();

		return contador;
	}

	/**
	 * @param args carpeta
	 * @throws IOException e
	 * @throws NullPointerException e
	 */
	public static void main(String[] args) throws IOException, NullPointerException {
		SumadorFichero sumadorFichero = new SumadorFichero();
		String nCarpeta = args[0];
		int contador = 0;

		String sCarpAct = System.getProperty("user.dir");
		File carpeta = new File(sCarpAct + nCarpeta);
		String[] listado = carpeta.list();

		for (int i = 0; i < Objects.requireNonNull(listado).length; i++) {
			String p = Paths.get(listado[i]).getFileName().toString();
			contador += sumadorFichero.sumador(p);
		}

		FileWriter fileWriter = new FileWriter("salida_SumadorFichero.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(contador);
		printWriter.close();
		File lockFile = new File("sumadorFichero.lock");
	}
}
