package ejemplos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LanzadorProcesos {

	// método para lanzar procesos sin parámetros
	public void lanzar(String clase) {		
		ProcessBuilder pb;
		try {
			pb = new ProcessBuilder("java", "-cp", "bin", clase);
			pb.redirectError(new File("error.txt"));
			pb.redirectOutput(new File("salida.txt"));
			pb.start();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// método para lanzar sumadores
	public void lanzarSumador(String id, String n1, String n2) {
		ProcessBuilder pb;
		try {
			pb = new ProcessBuilder("java", "-cp", "bin", "ejemplos.Sumador", n1, n2, id);
			pb.redirectError(new File("error_" + id + ".txt"));
			pb.redirectOutput(new File("salida_" + id +".txt"));
			pb.start();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		LanzadorProcesos l = new LanzadorProcesos();
		File file1 = new File("sumador_1.txt");
		File file2 = new File("sumador_2.txt");	
		// Nos aseguramos de que los ficheros no existen ya de una ejecución anterior
		if(file1.isFile()) {
			file1.delete();			
		}		
		if(file2.isFile()) {
			file2.delete();
		}
		// lanzamos los sumadores
		l.lanzarSumador("1", "1", "10");
		l.lanzarSumador("2", "10", "20");
		// esperamos a que los procesos acaben y hayan escrito y cerrado los ficheros donde estará su salida
		while(!file1.isFile() || !file2.isFile()) {
			System.out.println("Esperando");
			Thread.sleep(200);
		}
		// puede transcurrir un tiempo entre que un proceso crea un fichero hasta que escribe
		// el contenido en el mismo, por lo tanto, esperamos un poco a que acaben de escribir el contenido en los ficheros
		Thread.sleep(500);
		// los sumadores ya han acabado, abrimos sus ficheros de salida y leemos su contenido
		BufferedReader reader = new BufferedReader(new FileReader("sumador_1.txt"));
		String currentLine;		
		while((currentLine = reader.readLine()) != null) {
			System.out.println(currentLine);				
		}
		reader.close();
		
		reader = new BufferedReader(new FileReader("sumador_2.txt"));				
		while((currentLine = reader.readLine()) != null) {
			System.out.println(currentLine);				
		}
		reader.close();
		System.out.println("Procesos terminados.");		
	}
}
