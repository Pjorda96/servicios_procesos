package sockets1;

import java.io.File;
import java.io.IOException;

public class ClienteServidorProcess {

	public static void main(String[] args) throws InterruptedException {
		ProcessBuilder pb1;
		ProcessBuilder pb2;

		try {
			pb1 = new ProcessBuilder("java", "-cp", "bin", "sockets1.ServidorProcess");
			pb1.redirectError(new File("serverError.txt"));
			pb1.redirectOutput(new File("serverOutput.txt"));

			pb2 = new ProcessBuilder("java", "-cp", "bin", "sockets1.ClienteProcess");
			pb2.redirectError(new File("clientError.txt"));
			pb2.redirectOutput(new File("clientOutput.txt"));

			pb1.start();
			pb2.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
