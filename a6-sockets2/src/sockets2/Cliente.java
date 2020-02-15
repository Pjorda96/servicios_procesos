package sockets2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente implements Runnable{

	@Override
	public void run() {
		InetSocketAddress direccion = new InetSocketAddress("localhost", 9876);
		Socket socket = new Socket();
		try {
			socket.setReuseAddress(true);
			socket.connect(direccion);
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader bfr = new BufferedReader(isr);
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println("Nombre: " + Thread.currentThread().getName());
			pw.println("Segunda l�nea");
			pw.println("Tercera l�nea");
			pw.println("fin");
			pw.flush();
			String resultado = bfr.readLine();
			System.out.println("El resultado es: "+ resultado);
			bfr.close();
			pw.close();
			isr.close();
			socket.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		marke.Cliente cliente	= new marke.Cliente();
		cliente.run();
	}
}
