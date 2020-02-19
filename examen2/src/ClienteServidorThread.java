import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class ClienteServidorThread {

	public static void main(String[] args) throws InterruptedException, NoSuchPaddingException, NoSuchAlgorithmException {
		GestorCifrado gestorCifServidor = new GestorCifrado();
		ServidorThread servidor = new ServidorThread(gestorCifServidor);
		Thread threadServidor = new Thread(servidor);
		threadServidor.start();
		int contCliente = 0;
		while(true) {
			ClienteThread cliente = new ClienteThread();
			Thread threadCliente = new Thread(cliente);
			threadCliente.setName("" + contCliente);
			threadCliente.start();
			contCliente++;
			ClienteThread cliente2 = new ClienteThread();
			Thread threadCliente2 = new Thread(cliente2);
			threadCliente2.setName("" + contCliente);
			threadCliente2.start();

			threadCliente.join();
			threadCliente2.join();
			System.out.println("Acaban los dos hilos");
			Thread.sleep(1000);
			contCliente++;
		}
	}

}
