package sockets1;

public class ClienteServidorThread {

	public static void main(String[] args) throws InterruptedException {
		ServidorThread servidor = new ServidorThread();
		Thread threadServidor = new Thread(servidor);
		threadServidor.start();
		int contCliente = 0;
		while(true) {
			ClienteThread cliente = new ClienteThread();
			Thread threadCliente = new Thread(cliente);
			threadCliente.setName("" + contCliente);
			threadCliente.start();
			threadCliente.join();
			Thread.sleep(1000);
			contCliente++;
		}
	}

}
