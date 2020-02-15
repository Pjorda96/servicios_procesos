package sockets1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServidorThread implements Runnable {

	@Override
	public void run() {
		System.out.println("Arrancado el servidor");
		ServerSocket socketEscucha = null;
		try {
			socketEscucha = new ServerSocket(9876);
		} catch (IOException e) {
			System.out.println("No se pudo poner un socket " + "a escuchar en TCP 9876");
			return;
		}
		while (true) {
			try {
				Socket conexion = socketEscucha.accept();
				System.out.println("Conexion recibida!");
				InputStream is = conexion.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader bf = new BufferedReader(isr);
				String peticion = bf.readLine();

				int resultado = 0;

				while(!peticion.contentEquals("fin")) {
					this.getParams(peticion.split(" "));

					resultado = getResult(peticion);

					System.out.println("Peticion recibida en servidor: " + peticion);
					peticion = bf.readLine();
				}

				OutputStream os = conexion.getOutputStream();
				PrintWriter pw = new PrintWriter(os);
				pw.write("He recibido tu peticion. El resultado es: " + resultado + "\n");
				pw.flush();
				conexion.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}		
	}

	public void getParams(String[] params) throws NumberFormatException {
		System.out.println("El primer elemento es: " + params[0]);
		System.out.println("El segundo elemento es: " + params[1]);
		System.out.println("El tercer elemento es: " + params[2]);
	}

	public int getResult(String param) {
		String[] params = param.split(" ");
		int first = Integer.parseInt(params[0]);
		String operando = params[1];
		int second = Integer.parseInt(params[2]);

		if (operando.equals("sum")) return first + second;
		else return first - second;
	}
}
