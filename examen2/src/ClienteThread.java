import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteThread implements Runnable {

	@Override
	public void run() {
		String message = "Hola que tal?";
		InetSocketAddress direccion = new InetSocketAddress("localhost", 9876);
		Socket socket = new Socket();		
		try {
			GestorCifrado gestorCif = new GestorCifrado();
			socket.setReuseAddress(true);
			socket.connect(direccion);

			gestorCif.sendPublicKey(socket.getOutputStream());
			gestorCif.receivePublicKey(socket.getInputStream());

			gestorCif.sendEncryptedMessage(message.getBytes(), socket.getOutputStream());

			String decryptedMessage = gestorCif.receiveEncryptedMessage(socket.getInputStream());
			System.out.println("Cliente: " + decryptedMessage);
			socket.close();
		} catch (Exception e) {
			System.out.println(e);
		}		
	}
}
