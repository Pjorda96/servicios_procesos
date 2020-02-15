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
		int counter = 0;

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

				List<String> params = new ArrayList<>();

				while(!peticion.contentEquals("fin")) {
					this.getParam(peticion, counter);
					params.add(peticion);

					System.out.println("Peticion recibida en servidor: " + peticion);
					peticion = bf.readLine();

					counter++;
				}

				int resultado = getResult(params);

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

	public void getParam(String param, int counter) throws NumberFormatException {
		String order;

		switch (counter) {
			case 0: order = "primer"; break;
			case 1: order = "segundo"; break;
			case 2: order = "tercer"; break;
			default: order = "siguiente";
		}

		System.out.println("El " + order + " elemento es: " + param);
	}

	public int getResult(List<String> params) {
		int first = Integer.parseInt(params.get(0));
		String operando = params.get(1);
		int second = Integer.parseInt(params.get(2));

		if (operando.equals("sum")) return first + second;
		else return first - second;
	}
}
