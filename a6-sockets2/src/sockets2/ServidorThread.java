package sockets2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorThread implements Runnable {

	private ExecutorService executor = Executors.newFixedThreadPool(10);

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
				Worker worker = new Worker(conexion);
				executor.execute(worker);
			} catch (Exception e) {
				try {
					socketEscucha.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println(e);
			}
		}		
	}

	public static void main(String[] args) {
		ServidorThread servidor = new ServidorThread();
		servidor.run();
	}
}
