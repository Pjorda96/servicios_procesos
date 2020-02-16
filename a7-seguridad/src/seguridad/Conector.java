package seguridad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Conector {

	public static void main(String[] args) {
		String destino = "www.google.com";
		int puertoDestino = 80;
		Socket socket = new Socket();
		InetSocketAddress direccion = new InetSocketAddress(destino, puertoDestino);
		try {
			socket.connect(direccion);
			// Si llegamos aqu�, es que la conexi�n s� se hizo.
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			// Flujos que manejan caracteres
			InputStreamReader isr = new InputStreamReader(is);
			OutputStreamWriter osw = new OutputStreamWriter(os);

			// Flujos de l�neas
			BufferedReader bReader = new BufferedReader(isr);
			PrintWriter pWriter = new PrintWriter(osw);

			pWriter.println("GET /index.html");
			pWriter.flush();
			String linea;			
			while ((linea = bReader.readLine()) != null) {				
				System.out.println(linea);
			}
			pWriter.close();
			bReader.close();
			isr.close();
			osw.close();
			is.close();
			os.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("No se pudo establecer la conexion " + " o hubo un fallo al leer datos.");
		}
	}
}
