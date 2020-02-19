package examen2;

import java.net.Socket;

public class Worker implements Runnable {

	private Socket conexion;
	private GestorCifrado gestorCif;

	public Worker(Socket c, GestorCifrado gc) {
		this.conexion = c;
		gestorCif = gc;
	}

	@Override
	public void run() {
		String message = "Muy bien";
		try {
			gestorCif.receivePublicKey(conexion.getInputStream());
			gestorCif.sendPublicKey(conexion.getOutputStream());

			String decryptedMessage = gestorCif.receiveEncryptedMessage(conexion.getInputStream());
			System.out.println("Servidor: " + decryptedMessage);

			gestorCif.sendEncryptedMessage(message.getBytes(), conexion.getOutputStream());
			conexion.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
