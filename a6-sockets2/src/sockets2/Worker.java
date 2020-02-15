package sockets2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Worker implements Runnable {

	private Socket conexion;

	public Worker(Socket c) {
		this.conexion = c;
	}

	@Override
	public void run() {
		try {
			InputStream is = conexion.getInputStream();
			OutputStream os = conexion.getOutputStream();
			InputStreamReader isr = new InputStreamReader(is);
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedReader bf = new BufferedReader(isr);
			PrintWriter pw = new PrintWriter(osw);
			String lineaPeticion = bf.readLine();
			while (!lineaPeticion.contentEquals("fin")) {
				System.out.println("Linea peticion: " + lineaPeticion);
				lineaPeticion = bf.readLine();
			}
			Thread.sleep(2000);
			pw.write("He recibido tu peticion\n");
			pw.flush();
			conexion.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
