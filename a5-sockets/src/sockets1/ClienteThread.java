package sockets1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClienteThread implements Runnable {

	@Override
	public void run() {
		InetSocketAddress direccion = new InetSocketAddress("localhost", 9876);
		Socket socket = new Socket();		
		try {
			//socket.setReuseAddress(true);
			socket.connect(direccion);
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader bfr = new BufferedReader(isr);
			PrintWriter pw = new PrintWriter(socket.getOutputStream());

			List<String> operacion = generarOperacion();

			pw.println(operacion.get(0));
			pw.println(operacion.get(1));
			pw.println(operacion.get(2));
			pw.println("fin");
			pw.flush();
			String resultado = bfr.readLine();
			System.out.println("El resultado fue:" + resultado);			
			socket.close();
		} catch (Exception e) {
			System.out.println(e);
		}		
	}

	public List<String> generarOperacion() {
		List<String> operacion = new ArrayList<>();
		Random rd1 = new Random();
		Random rd2 = new Random();
		Random rd3 = new Random();

		operacion.add("" + rd1.nextInt(100) + 1);

		if (rd2.nextInt(100) + 1 <= 50) operacion.add("sum");
		else operacion.add("res");

		operacion.add("" + rd3.nextInt(100) + 1);

		return operacion;
	}
}
