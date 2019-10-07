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
	
	public void ejercicio1() throws IOException, InterruptedException {
		// creamos un fichero de sincronización por cada proceso que vayamos a crear
		// mientras estos ficheros existan, los procesos estarán en marcha.
		// Cada proceso cargado, se encargará de eliminar su fichero de sincronización
		// cuando acabe
		File file1 = new File("sumador_1.lock");
		File file2 = new File("sumador_2.lock");
		file1.createNewFile();
		file2.createNewFile();
		// lanzamos los sumadores
		this.lanzarSumador("1", "1", "10");
		this.lanzarSumador("2", "10", "20");
		// esperamos a que los procesos acaben y hayan escrito y cerrado los ficheros donde estará su salida
		// cuando un proceso acaba, borra su fichero .lock, por lo tanto, para saber si un proceso 
		// ha acabado, debemos mirar si el fichero .lock de ese proceso aún existe.
		while(file1.isFile() || file2.isFile()) {
			System.out.println("Esperando a que terminen los sumadores");
			Thread.sleep(200);
		}
		System.out.println("Sumadores terminados.");
		// los sumadores ya han acabado, abrimos sus ficheros de salida y leemos su contenido
		BufferedReader reader = new BufferedReader(new FileReader("sumador_1.txt"));
		String currentLine;		
		while((currentLine = reader.readLine()) != null) {
			System.out.println("Resultado sumador 1 = " + currentLine);				
		}
		reader.close();				
		reader = new BufferedReader(new FileReader("sumador_2.txt"));				
		while((currentLine = reader.readLine()) != null) {
			System.out.println("Resultado sumador 2 = " + currentLine);				
		}
		reader.close();				
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		LanzadorProcesos l = new LanzadorProcesos();
		l.ejercicio1();
	}
}
