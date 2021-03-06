package actividad2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Sumador {
	
	public int sumar(int n1, int n2) {
		int resultado = 0;
		for(int i = n1; i <= n2; i++) {
			resultado += i;
		}
		return resultado;
	}	

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Sumador sumador = new Sumador();
		int n1 = Integer.parseInt(args[0]);
		int n2 = Integer.parseInt(args[1]);
		String id = args[2];
		int resultado = sumador.sumar(n1, n2);		
		FileWriter fileWriter = new FileWriter("sumador_" + id + "1.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(resultado);		
		printWriter.close();
		File lockFile = new File("sumador_" + id + ".lock");
		lockFile.delete();
	}
}
