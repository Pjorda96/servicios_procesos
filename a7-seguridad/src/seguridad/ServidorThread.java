package seguridad;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorThread implements Runnable {

	@Override
	public void run() {
		String message = "Muy bien";
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
				GestorCifrado gestorCif = new GestorCifrado();
				Socket conexion = socketEscucha.accept();
				System.out.println("Conexion aceptada!");

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
}
